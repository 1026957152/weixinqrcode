package com.coalvalue.service;


import com.alibaba.fastjson.JSON;
import com.coalvalue.domain.entity.WxPermanentQrcode;
import com.coalvalue.domain.entity.WxTemporaryQrcode;
import com.coalvalue.domain.json.AccessTokenEventJson;

import com.coalvalue.domain.json.WeixinRequestMessageTypeEvent_ScanJson;
import com.coalvalue.domain.json.WeixinRequestMessageTypeEvent_Scan_ResultJson;
import com.coalvalue.repository.WxPermanentQrcodeRepository;
import com.coalvalue.weixin.pojo.Token;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

/**
 * Created by silence on 2016/2/21.
 */
@Service("accessTokenManagerService")
public class AccessTokenManagerService extends BaseServiceImpl {

    @Autowired

    private WxPermanentQrcodeRepository wxPermanentQrcodeRepository;


    @Autowired

    private TempQrcodeService tempQrcodeService;

    @Autowired
    @Qualifier(value="kafkaTemplateJson")
    private KafkaTemplate kafkaTemplateJson;


    @KafkaListener(id="a" ,topics = "weixin_message_type_event_scan_kafka_json", groupId = "apl_fuck_4_access_token",containerFactory = "kafkaListenerContainerFactory_JSON")

    public void listen_message_TransportEvent(WeixinRequestMessageTypeEvent_ScanJson message) {


        if( Integer.valueOf(message.Key)<100000) {

            WxPermanentQrcode wxPermanentQrcode = wxPermanentQrcodeRepository.findByKeyAndAppId(Integer.valueOf(message.Key), message.AppId);
            System.out.println("扫到了 永久性的 二维码" + wxPermanentQrcode.getObjectUuid() + "===" + wxPermanentQrcode.getType());
            if (wxPermanentQrcode != null) {
                WeixinRequestMessageTypeEvent_Scan_ResultJson capacityEventJSON = new WeixinRequestMessageTypeEvent_Scan_ResultJson();
                capacityEventJSON.Behavior = "Create";
                capacityEventJSON.Timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
                capacityEventJSON.AppId = (message.AppId);
                capacityEventJSON.OpenId = message.OpenId;
                capacityEventJSON.Key = message.Key;
                capacityEventJSON.Subscribe = message.Subscribe;
                if(wxPermanentQrcode.getInfo() == null){
                    Map map = new HashMap();
                    map.put("objectId",wxPermanentQrcode.getObjectUuid());
                    capacityEventJSON.Info = JSON.toJSONString(map);
                }else{
                    capacityEventJSON.Info = wxPermanentQrcode.getInfo();
                }

                capacityEventJSON.ObjectId = wxPermanentQrcode.getObjectUuid();
                capacityEventJSON.Ttype = wxPermanentQrcode.getType();


                Map user = JSON.parseObject(capacityEventJSON.Info,Map.class);

                // capacityEventJSON.Ttype="SCAN";

                kafkaTemplateJson.send("weixin_message_type_event_scan_result_kafka_json",capacityEventJSON);


                System.out.println("FUCK MESSAGE  MessageEventJSON MessageEventJsonSinkTopic" + capacityEventJSON.toString());
                System.out.println("JSON.toJSONString Info: " + user.toString());
            }




        }else {
            WxTemporaryQrcode wxTemporaryQrcode = tempQrcodeService.getTempByKey(Integer.valueOf(message.Key));


            WeixinRequestMessageTypeEvent_Scan_ResultJson capacityEventJSON = new WeixinRequestMessageTypeEvent_Scan_ResultJson();
            capacityEventJSON.Behavior = "Create";
            capacityEventJSON.Timestamp = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
            capacityEventJSON.AppId = (message.AppId);
            capacityEventJSON.OpenId = message.OpenId;
            capacityEventJSON.Key = message.Key;
            capacityEventJSON.Subscribe = message.Subscribe;
            capacityEventJSON.Info = wxTemporaryQrcode.getInfo();
            capacityEventJSON.ObjectId = wxTemporaryQrcode.getObjectUuid();
            capacityEventJSON.Ttype = wxTemporaryQrcode.getType();

            Map user = JSON.parseObject(wxTemporaryQrcode.getInfo(),Map.class);

            // capacityEventJSON.Ttype="SCAN";

            kafkaTemplateJson.send("weixin_message_type_event_scan_result_kafka_json",capacityEventJSON);


            System.out.println("FUCK MESSAGE  MessageEventJSON MessageEventJsonSinkTopic" + message.toString());
            System.out.println("JSON.toJSONString Info: " + user.toString());
        }

    }



    Map<String,AccessTokenEventJson> map = new HashMap();

    @KafkaListener(id="aa__9_" ,topics = "access-token-event-json", groupId = "aplog_fuck__44_access_token",containerFactory = "kafkaListenerContainerFactory_JSON")

    public void listen_message_TransportEvent(AccessTokenEventJson message) {

        System.out.println("FUCK MESSAGE  MessageEventJSON MessageEventJsonSinkTopic" + message.toString());


        System.out.println("11111111111111  Received Messasge in group foo: " + message.toString());


        map.put(message.AppId,message);

    }



/*
    static {


        String appId = WeixinUrlFilte_delivery.Constants_CORPID;
        String appSecret =  WeixinUrlFilte_delivery.APP_SECRET;// "APPSECRET";
        AccessTokenUpdate accessTokenUpdate = new AccessTokenUpdate(appId,appSecret,"delivery");
        maps.add(accessTokenUpdate);
        // 第三方用户唯一凭证

        // 第三方用户唯一凭证密钥

        appId = WeixinUrlFilter_trade.Constants_CORPID;
        appSecret = WeixinUrlFilter_trade.APP_SECRET;// "APPSECRET";
        accessTokenUpdate = new AccessTokenUpdate(appId,appSecret,"trade");
        maps.add(accessTokenUpdate);



        appId = WeixinUrlFilte_logistic.Constants_CORPID;
        appSecret = WeixinUrlFilte_logistic.APP_SECRET;// "APPSECRET";
        accessTokenUpdate = new AccessTokenUpdate(appId,appSecret,"logistic");
        maps.add(accessTokenUpdate);



    }
*/



    public Token getAccessToken(String corpId) {

        AccessTokenEventJson accessTokenEventJson = map.get(corpId);
        Token token = new Token();
        token.setAccessToken(accessTokenEventJson.Token);
        token.setAppId(accessTokenEventJson.AppId);
       // token.setReveiveTime(accessTokenEventJson.ReveiveTime);
        token.setExpiresIn(accessTokenEventJson.ExpiresIn);

        return token;

    }




}









