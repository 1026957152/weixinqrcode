package com.coalvalue.service;


import com.coalvalue.domain.entity.WxPermanentQrcode;
import com.coalvalue.domain.entity.WxTemporaryQrcode;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import qrcode.QrcodeOuterClass;

import java.util.Optional;

/**
 * Created by silence yuan on 2015/7/25.
 */
public interface ScanIdService extends BaseService {

    QrcodeOuterClass.QrcodeType permanentOrTempBykey(Integer key) ;


    Optional<String> permanentOrTempkey(String no, QrcodeOuterClass.QrcodeType type);
}
