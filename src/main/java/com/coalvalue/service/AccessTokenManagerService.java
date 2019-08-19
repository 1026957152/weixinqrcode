package com.coalvalue.service;


import com.coalvalue.domain.json.AccessTokenEventJson;

import com.coalvalue.weixin.pojo.Token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by silence on 2016/2/21.
 */
@Service("accessTokenManagerService")
public class AccessTokenManagerService extends BaseServiceImpl {




    @Autowired
    @Qualifier(value="kafkaTemplateJson")
    private KafkaTemplate kafkaTemplateJson;

    private static volatile Token accessToken;
    private static volatile LocalDateTime lastUpdateTime = LocalDateTime.now();

    Map<String,AccessTokenEventJson> map = new HashMap();

    @KafkaListener(id="aa__9_" ,topics = "access-token-event-json", groupId = "aplog_fuck__44_access_token",containerFactory = "kafkaListenerContainerFactory_JSON")

    public void listen_message_TransportEvent(AccessTokenEventJson message) {

        System.out.println("FUCK MESSAGE  MessageEventJSON MessageEventJsonSinkTopic" + message.toString());


        System.out.println("11111111111111  Received Messasge in group foo: " + message.toString());


/*
        TransportEventJson capacityEventJSON = new TransportEventJson(message.value());
        kafkaTemplateJson.send("transport-event-json",capacityEventJSON);

        String appId = WeixinUrlFilte_delivery.Constants_CORPID;
        String appSecret =  WeixinUrlFilte_delivery.APP_SECRET;// "APPSECRET";
        AccessTokenUpdate accessTokenUpdate = new AccessTokenUpdate(appId,appSecret,"delivery");
        */

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









