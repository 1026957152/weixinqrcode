package com.coalvalue.service;


import com.coalvalue.domain.OperationResult;
import com.coalvalue.domain.entity.*;
import com.coalvalue.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface TempQrcodeService extends BaseService {


    WxTemporaryQrcode getMemoryTemporaryQrcode(String no,String appId);


    @Transactional
    WxTemporaryQrcode getTempByObjectId(Integer id);
}
