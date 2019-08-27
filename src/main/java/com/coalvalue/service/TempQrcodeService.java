package com.coalvalue.service;


import com.coalvalue.domain.entity.*;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface TempQrcodeService extends BaseService {


    WxTemporaryQrcode getMemoryTemporaryQrcode(String no, String info, WxQrcodeTypeEnum type_enum, String appId);



    WxTemporaryQrcode getTempByKey(Integer id);
}
