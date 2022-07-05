package com.coalvalue.service;

import com.coalvalue.configuration.CommonConstant;
import com.coalvalue.domain.entity.WxTemporaryQrcode;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import com.coalvalue.domain.json.WeixinQrcodeEventJson;
import com.coalvalue.weixin.pojo.WeixinQRCode;
import com.coalvalue.weixin.util.AdvancedUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import qrcode.QrcodeOuterClass;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by silence yuan on 2015/7/25.
 */

@Service("scanIdService")
public class ScanIdServiceImpl extends BaseServiceImpl implements ScanIdService {


    @Autowired
    private MySQLMaxValueIncrementer wxTemporaryQrcodeScanIdGenerator;
    @Autowired
    private MySQLMaxValueIncrementer wxPermanentQrcodeScanIdGenerator;



    @Override
    public QrcodeOuterClass.QrcodeType permanentOrTempBykey(Integer key_) {


        if(key_ < 1000000){
            return QrcodeOuterClass.QrcodeType.PERMANENT;
        }else{
            return QrcodeOuterClass.QrcodeType.TEMP;
        }
    }


    @Override
    public Optional<String> permanentOrTempkey(String no, QrcodeOuterClass.QrcodeType type) {
        if(type ==  QrcodeOuterClass.QrcodeType.PERMANENT){
            return Optional.of(wxPermanentQrcodeScanIdGenerator.nextStringValue());
        }
        if(type ==  QrcodeOuterClass.QrcodeType.TEMP){
            return Optional.of(wxTemporaryQrcodeScanIdGenerator.nextStringValue());
        }

        return Optional.empty();
    }








}
