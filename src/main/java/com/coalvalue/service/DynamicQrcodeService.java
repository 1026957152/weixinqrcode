package com.coalvalue.service;


import java.util.Optional;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface DynamicQrcodeService extends BaseService {


    String dynamic(String id);

    Optional dynamic_by_qrcode(String content);
}
