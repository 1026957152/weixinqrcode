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
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by silence yuan on 2015/7/25.
 */

@Service("tempQrcodeService")
public class TempQrcodeServiceImpl extends BaseServiceImpl implements TempQrcodeService {

    protected transient Logger logger = LoggerFactory.getLogger(getClass());    public static String path_qrcode_image_resoure_path = "path_qrcode_image_resoure_path";
    @Autowired
    @Qualifier(value="kafkaTemplateJson")
    private KafkaTemplate kafkaTemplateJson;
    @Autowired
    private AccessTokenManagerService accessTokenManagerService;



    @Autowired
    private MySQLMaxValueIncrementer wxTemporaryQrcodeScanIdGenerator;


    public WxTemporaryQrcode createMemaryTimeSilence_type(String uuid, String appId, Integer scanId, String content, String ticket, String info, WxQrcodeTypeEnum type_enum) {

        logger.debug("createTimeSilence_type {} {} {} {} {} {}",uuid,appId,scanId,content,ticket );


        WxTemporaryQrcode wxScanGeneral =  new WxTemporaryQrcode();
        wxScanGeneral.setObjectId(uuid);
        wxScanGeneral.setContent(content);
        wxScanGeneral.setTicket(ticket);

        wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);

        wxScanGeneral.setAppId(appId);
        wxScanGeneral.setInfo(info);

        wxScanGeneral.setKey(scanId);
        wxScanGeneral.setType(type_enum.getText());

        return wxScanGeneral;

    }


    Map<Integer, WxTemporaryQrcode> stores = new HashMap<>();
    @Override
    public WxTemporaryQrcode getMemoryTemporaryQrcode(String no, String info, WxQrcodeTypeEnum type_enum, String appId) {
        String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();


        Integer scanId = wxTemporaryQrcodeScanIdGenerator.nextIntValue();
        scanId= scanId+100000;
        try {
            WeixinQRCode ticket = AdvancedUtil.createTemporaryQRCode(accessToken, 2592000, scanId);
            if (ticket != null) {


                WxTemporaryQrcode wxTemporaryQrcode =  createMemaryTimeSilence_type(no,appId,scanId,ticket.getUrl(),ticket.getTicket(),info,type_enum);
                stores.put(wxTemporaryQrcode.getKey(),wxTemporaryQrcode);

                WeixinQrcodeEventJson capacityEventJSON = new WeixinQrcodeEventJson();
                capacityEventJSON.setBehavior("Create");
                capacityEventJSON.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                capacityEventJSON.setAppId(wxTemporaryQrcode.getAppId());
                capacityEventJSON.setScanId(wxTemporaryQrcode.getKey());
                capacityEventJSON.setTtype(wxTemporaryQrcode.getType());
                capacityEventJSON.setStatus(wxTemporaryQrcode.getStatus());


                capacityEventJSON.setId(UUID.randomUUID().toString());

                capacityEventJSON.setSubScene(1);
                capacityEventJSON.setTemporaryOrPermanent("Temporary");
                capacityEventJSON.setUrl("_");
                capacityEventJSON.setContent(wxTemporaryQrcode.getContent());
                capacityEventJSON.setTicket(wxTemporaryQrcode.getTicket());

                capacityEventJSON.setTtype(wxTemporaryQrcode.getType());
                capacityEventJSON.setObjectUuid(wxTemporaryQrcode.getObjectId());
                capacityEventJSON.setQrCode(wxTemporaryQrcode.getQrCode());
                capacityEventJSON.setInfo(wxTemporaryQrcode.getInfo());
                capacityEventJSON.setType(wxTemporaryQrcode.getType());


                kafkaTemplateJson.send("weixin-qrcode-event-json",capacityEventJSON);


                logger.debug("--------------{}", wxTemporaryQrcode.toString());
                return wxTemporaryQrcode;
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("失败");
        }
        return null;
    }






    @Transactional
    public WxTemporaryQrcode getTempByKey(Integer id) {


        return stores.get(id);



    }









}
