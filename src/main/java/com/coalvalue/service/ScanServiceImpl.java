package com.coalvalue.service;


import com.coalvalue.domain.entity.Scan;
import com.coalvalue.repository.ScenarioRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * Created by silence yuan on 2015/7/25.
 */

@Service("scanService")
public class ScanServiceImpl extends BaseServiceImpl implements ScanService {


    @Autowired
    private ScenarioRepository scenarioRepository;
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

    @Override
    public Page<Scan> listScan(PageRequest of) {
        return scenarioRepository.findAll(of);
    }

    @Override
    public Scan getScan(String id) {
        return scenarioRepository.findByUuid(id);
    }

    @Override
    @Transactional
    public Scan createScan(String occupationId, String ReferenceId, String ReferenceType) {
        Scan scan = new Scan();
        scan.setStatus("valid");

        scan.setOccupationId(occupationId);
        scan.setReferenceId(ReferenceId);
        scan.setReferenceType(ReferenceType);
        return scenarioRepository.save(scan);
    }


}
