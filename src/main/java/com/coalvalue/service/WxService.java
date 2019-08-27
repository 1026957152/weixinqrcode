package com.coalvalue.service;


import com.coalvalue.domain.OperationResult;
import com.coalvalue.domain.entity.*;

import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import com.coalvalue.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface WxService extends BaseService {


    WxPermanentQrcode getByCompany_type_new(String uuid, String resourceType, String type, String appId);



    WxTemporaryQrcode getByTimeSilence_type(String no, String type, String appId);

    WxTemporaryQrcode getMemoryTemporaryQrcode(String no, String type, String appId);

    WxTemporaryQrcode getTempByObjectId(String no, Integer id, String text, String wxQrcodeTypeScatteredOrder, int expireSeconds, int tryCount);


    WxTemporaryQrcode getTempByObjectId(WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm);



    OperationResult getByCompany_type_new_RETURN(String uuid, String text, String type, String constants_corpid);

    WxPermanentQrcode getPermanentQrcode(String uuid, String info, WxQrcodeTypeEnum type_enum, String weixinUrlFilte_delivery);
}
