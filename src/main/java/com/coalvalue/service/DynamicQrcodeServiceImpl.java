package com.coalvalue.service;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
/**
 * Created by silence yuan on 2015/7/25.
 */

@Service("dynamicQrcodeService")
public class DynamicQrcodeServiceImpl extends BaseServiceImpl implements DynamicQrcodeService {

    protected transient Logger logger = LoggerFactory.getLogger(getClass());    public static String path_qrcode_image_resoure_path = "path_qrcode_image_resoure_path";



    static LoadingCache<String, Optional<String>> cache_by_content = CacheBuilder.newBuilder()
            .expireAfterAccess(60, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<String, Optional<String>>() {
                        public Optional<String> load(String key)  {
                            return Optional.empty();
                        }
                    });
    static LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .expireAfterAccess(60, TimeUnit.SECONDS)
            .build(
                    new CacheLoader<String, String>() {
                        public String load(String key)  {
                            String alphabetic = RandomStringUtils.randomAlphabetic(10);
                            cache_by_content.put(alphabetic,Optional.of(key));
                            return alphabetic;
                        }
                    });

    @Override
    public String dynamic(String id) {



        try {
            String content =  cache.get(id);
            System.out.println("-------------------- 根据 职工id 获取 二维码内容"+content);
            Optional<String> aaaaaaaaaa = cache_by_content.getUnchecked(content);

            System.out.println("-------------------- 根据  二维码内容 获取 id "+aaaaaaaaaa.get());

            return content;

        } catch (
                ExecutionException e) {

            e.printStackTrace();
        }


        return null;

    }

    @Override
    public Optional dynamic_by_qrcode(String content) {
        Optional<String> aaaaaaaaaa = cache_by_content.getUnchecked(content);
        System.out.println("-------------------- 根据  二维码内容 获取 id "+aaaaaaaaaa.get());

            return aaaaaaaaaa;

    }


}
