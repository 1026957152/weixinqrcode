package com.coalvalue.api;


import com.coalvalue.domain.OperationResult;

import com.coalvalue.domain.entity.WxPermanentQrcode;
import com.coalvalue.domain.entity.WxTemporaryQrcode;

import com.coalvalue.domain.enums.ResourceType;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;

import com.coalvalue.repository.WxPermanentQrcodeRepository;
import com.coalvalue.service.TempQrcodeService;
import com.coalvalue.service.WxService;
import com.coalvalue.service.WxServiceImpl;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

//import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by zohu on 6/29/2015.
 */
@RestController
@RequestMapping("/api/v1/qrcode")
public class V2QrcodeRestController {

    protected transient Logger logger = LoggerFactory.getLogger(V2QrcodeRestController.class);
//    @Autowired
//    StringRedisTemplate stringRedisTemplate;


    String WeixinUrlFilte_delivery= "wx6d7f2fec44663493";;
    String WeixinUrlFilter_trade= "wx242f2ca59a3973bf";

    @Autowired
    private WxService wxService;


    @Autowired
    private WxPermanentQrcodeRepository wxGeneralRepository;

    @Autowired
    private TempQrcodeService tempQrcodeService;


    public final static Cache<String,WxTemporaryQrcode> cache= CacheBuilder.newBuilder()
            //设置cache的初始大小为10，要合理设置该值  
            .initialCapacity(10)
            //设置并发数为5，即同一时间最多只能有5个线程往cache执行写入操作  
            .concurrencyLevel(5)
            //设置cache中的数据在写入之后的存活时间为10秒  
            .expireAfterWrite(60*30,TimeUnit.SECONDS)
            .recordStats()
            // 设置缓存的移除通知
            .removalListener(new RemovalListener<Object, Object>() {
                public void onRemoval(RemovalNotification<Object, Object> notification) {
                    System.out.println(
                            notification.getKey() + " was removed, cause is " + notification.getCause());
                }
            })
            //构建cache实例  
            .build();






        @RequestMapping(method = RequestMethod.GET, value = "/{uuid}/{type}")
        public Map getQrcode(@PathVariable("uuid") String uuid,  @PathVariable("type") String type){

System.out.println("建立二维码，请求"+type+"--------------"+ uuid);
        Map map = new HashMap();

        if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER.getText().equals(type)){
            WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER;
            WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();
            createTemporaryQrcodeForm.setTtryCountr(1);
            createTemporaryQrcodeForm.setObjectUuid(uuid);
            createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);
            createTemporaryQrcodeForm.setItemType(ResourceType.DELIVERY_ORDER.getText());
            createTemporaryQrcodeForm.setType(qrtype.getText());
            createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());



            WxTemporaryQrcode wxTemporaryQrcode = wxService.getTempByObjectId(createTemporaryQrcodeForm);
            map.put("content", wxTemporaryQrcode.getContent());
            map.put("uuid", uuid);
            map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER.getText());


        }

        if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_coalpit.getText().equals(type)){
                WxPermanentQrcode wxPermanentQrcode = wxService.getByCompany_type_new(uuid,ResourceType.COMPANY.getText(), type, WeixinUrlFilte_delivery);
                map.put("content", wxPermanentQrcode.getContent());
                map.put("uuid", uuid);
                map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_coalpit.getText());
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_delivery.getText().equals(type)){
                WxPermanentQrcode wxPermanentQrcode = wxService.getByCompany_type_new(uuid,ResourceType.COMPANY.getText(), type, WeixinUrlFilte_delivery);
                map.put("content", wxPermanentQrcode.getContent());
                map.put("uuid", uuid);
                map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_delivery.getText());
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_logistics.getText().equals(type)){
                WxPermanentQrcode wxPermanentQrcode = wxService.getByCompany_type_new(uuid,ResourceType.COMPANY.getText(), type, WeixinUrlFilte_delivery);
                map.put("content", wxPermanentQrcode.getContent());
                map.put("uuid", uuid);
                map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_logistics.getText());
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_TRADER.getText().equals(type)){

                WxPermanentQrcode wxPermanentQrcode = wxService.getByCompany_type_new(uuid,ResourceType.COMPANY.getText(), type,WeixinUrlFilte_delivery);// WeixinUrlFilter_trade.Constants_CORPID);
                map.put("content", wxPermanentQrcode.getContent());
                map.put("uuid", uuid);
                map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_TRADER.getText());
            }
            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY.getText().equals(type)){

                OperationResult operationResult = wxService.getByCompany_type_new_RETURN(uuid,ResourceType.COMPANY.getText(), type,WeixinUrlFilte_delivery);// WeixinUrlFilter_trade.Constants_CORPID);
                if(operationResult.isSuccess()){
                    WxPermanentQrcode wxPermanentQrcode =(WxPermanentQrcode)operationResult.getResultObject();
                    map.put("content", wxPermanentQrcode.getContent());
                    map.put("uuid", uuid);
                    map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY.getText());
                }else{
                    map.put("content", operationResult.getErrorMessage());
                    map.put("uuid", uuid);
                    map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY.getText());
                }

            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_AUTO_SYSTEM_MANAGEMENT.getText().equals(type)){
                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_AUTO_SYSTEM_MANAGEMENT;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();
                    createTemporaryQrcodeForm.setTtryCountr(1);
                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_AUTO_SYSTEM_MANAGEMENT.getText());
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_CANVASSING.getText().equals(type)){

                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_CANVASSING;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();

                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type",qrtype );
            }



            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_CANVASSING_Conduction.getText().equals(type)){

                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_CANVASSING_Conduction;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();

                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type",qrtype );
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_DELIVERY_ORDER_Conduction.getText().equals(type)){

                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_DELIVERY_ORDER_Conduction;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();

                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilter_trade);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type",qrtype );
            }



            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_OPEN_ACCOUNT_Conduction.getText().equals(type)){

                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_OPEN_ACCOUNT_Conduction;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();

                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type",qrtype );
            }
            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_BIND_EMPLOYEE_ACCOUNT.getText().equals(type)){

                WxQrcodeTypeEnum qrtype = WxQrcodeTypeEnum.WX_QRCODE_TYPE_BIND_EMPLOYEE_ACCOUNT;
                WxTemporaryQrcode wxPermanentQrcode_cache = cache.getIfPresent(uuid);

                if(wxPermanentQrcode_cache == null){
                    WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();

                    createTemporaryQrcodeForm.setObjectUuid(uuid);
                    createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery);

                    createTemporaryQrcodeForm.setType(qrtype.getText());
                    createTemporaryQrcodeForm.setExpireSeconds(qrtype.getExpireSeconds());
                    wxPermanentQrcode_cache = wxService.getTempByObjectId(createTemporaryQrcodeForm);
                    cache.put(uuid,wxPermanentQrcode_cache);

                }


                map.put("content", wxPermanentQrcode_cache.getContent());
                map.put("uuid", uuid);
                map.put("type",qrtype );
            }


            return map;

    }



/*


    @JmsListener(destination =MessageConstants.topic_weixin_qrcode_request_create)
    public void receiveTemplateMessage(MapMessage message) {

        System.out.println(" ========== we have recieve message in queue   WITH CONTENT" + message.toString());
        //jmsSenderService.sendObjectMessage_topic_weixin_qrcode_request_create();

        try {
            Token token = accessTokenManagerService.getAccessToken(WeixinUrlFilter_trade.Constants_CORPID);
            String appId = token.getAppId();

            String type = message.getString("type");
            System.out.println(" ========== we have recieve message in queue    WITH CONTENT" + type);
            String companyNo = message.getString("companyNo");




            if(Constants.WX_QRCODE_TYPE_BIND_USER_ACCOUNT.equals(type)){
                Integer userId = message.getInt("userId");

                //wxService.getByCompany_type(company, type);

                wxService.getTempByObjectId(null, userId, ResourceType.USER.getText(), Constants.WX_QRCODE_TYPE_BIND_USER_ACCOUNT, 2592000,1);
            }
            if(Constants.WX_QRCODE_TYPE_STORAGESPACE.equals(type)){

                Integer companyId = message.getInt("companyId");
                wxService.getByStorage_type(companyId, type);

            }

            if(Constants.WX_QRCODE_TYPE_COMPANY_TRADER.equals(type)){
                Company company = companyRepository.findByCompanyNo(companyNo);
                wxService.getByCompany_type(company, type,appId);

            }
            if(Constants.WX_QRCODE_TYPE_COMPANY_LOGISTIC.equals(type)){
                Company company = companyRepository.findByCompanyNo(companyNo);
                wxService.getByCompany_type(company, type,appId);

            }

            if(Constants.WX_QRCODE_TYPE_DELIVERY_ORDER_DISTRRIBUTO.equals(type)){
                Integer itemId = message.getInt("itemId");

                WxTemporaryQrcode wxTemporaryQrcode  =  wxService.getByDelivery_order_distribute_type(itemId, type,appId);
                ActiveMQTopic destination = new ActiveMQTopic(MessageConstants.topic_weixin_qrcode_response_create);
                jmsTemplate.send(destination, new MessageCreator() {
                    public MapMessage createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("content", wxTemporaryQrcode.getContent());
                        mapMessage.setString("objectUuid", wxTemporaryQrcode.getObjectUuid());
                        mapMessage.setString("uuid", wxTemporaryQrcode.getUuid());

                        mapMessage.setString("type", Constants.WX_QRCODE_TYPE_DELIVERY_ORDER_DISTRRIBUTO);

                        return mapMessage;
                    }});


                System.out.println("发送 了map 消息");
            }


            if(Constants.WX_QRCODE_TYPE_COMPANY_TIMESILCE.equals(type)){

                String no = message.getString("objectUuid");

                WxTemporaryQrcode wxTemporaryQrcode = wxService.getByTimeSilence_type(no,type,appId);
                ActiveMQTopic destination = new ActiveMQTopic(MessageConstants.topic_weixin_qrcode_response_create);

                logger.debug("createTimeSilence_type {} {} {} {} {} {}",wxTemporaryQrcode.toString() );
                logger.debug("createTimeSilence_type {} {} {} {} {} {}",wxTemporaryQrcode.getObjectUuid() );
                logger.debug("createTimeSilence_type {} {} {} {} {} {}",wxTemporaryQrcode.getUuid() );
                logger.debug("createTimeSilence_type {} {} {} {} {} {}",wxTemporaryQrcode.getContent() );

                jmsTemplate.send(destination, new MessageCreator() {
                    public MapMessage createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("uuid", wxTemporaryQrcode.getUuid());
                        mapMessage.setString("objectUuid", wxTemporaryQrcode.getObjectUuid());
                        mapMessage.setString("content", wxTemporaryQrcode.getContent());
                        mapMessage.setString("type", Constants.WX_QRCODE_TYPE_COMPANY_TIMESILCE);
                        return mapMessage;
                    }});
                System.out.println("发送 了map 消息");
            }


            if(Constants.WX_QRCODE_TYPE_BIND_DISTRIBUTOR_USER_ACCOUNT.equals(type)){
                String userNo = message.getString("userNo");
                Employee employee = employeeRepository.findByUserNo(userNo);
                WxTemporaryQrcode wxTemporaryQrcode = wxService.getTempByObjectId(null, employee.getId(), ResourceType.EMPLOYEE.getText(),Constants.WX_QRCODE_TYPE_BIND_DISTRIBUTOR_USER_ACCOUNT, 2592000,1);





                ActiveMQTopic destination = new ActiveMQTopic(MessageConstants.topic_weixin_qrcode_response_create);
                jmsTemplate.send(destination, new MessageCreator() {
                    public MapMessage createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("content", wxTemporaryQrcode.getContent());
                        mapMessage.setString("userNo", userNo);

                        mapMessage.setString("type", Constants.WX_QRCODE_TYPE_BIND_DISTRIBUTOR_USER_ACCOUNT);

                        return mapMessage;
                    }});


                System.out.println("发送 了map 消息");
            }


            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER.getText().equals(type)){
                String no = message.getString("no");
                String id = message.getString("itemId");

                WxServiceImpl.CreateTemporaryQrcodeForm createTemporaryQrcodeForm = new WxServiceImpl.CreateTemporaryQrcodeForm();
                createTemporaryQrcodeForm.setTtryCountr(1);
                createTemporaryQrcodeForm.setNo(no);
                createTemporaryQrcodeForm.setCorpId(WeixinUrlFilte_delivery.Constants_CORPID);
                createTemporaryQrcodeForm.setItemId(Integer.valueOf(id));
                createTemporaryQrcodeForm.setItemType(ResourceType.DELIVERY_ORDER.getText());
                createTemporaryQrcodeForm.setType(WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER.getText());
                createTemporaryQrcodeForm.setExpireSeconds(2592000);

                WxTemporaryQrcode wxTemporaryQrcode = wxService.getTempByObjectId(createTemporaryQrcodeForm);


                ActiveMQTopic destination = new ActiveMQTopic(MessageConstants.topic_weixin_qrcode_response_create);
                jmsTemplate.send(destination, new MessageCreator() {
                    public MapMessage createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("content", wxTemporaryQrcode.getContent());
                        mapMessage.setString("id", id);
                        mapMessage.setString("no", no);
                        mapMessage.setString("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER.getText());

                        return mapMessage;
                    }});


                System.out.println("发送 了map 消息");
            }

            if(WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_BIND_AUTO_SYSTEM.getText().equals(type)){
                String objectUuid = message.getString("objectUuid");
                WxTemporaryQrcode wxTemporaryQrcode = wxService.getMemoryTemporaryQrcode(objectUuid, WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_BIND_AUTO_SYSTEM.getText(), appId);
                ActiveMQTopic destination = new ActiveMQTopic(MessageConstants.topic_weixin_qrcode_response_create);
                jmsTemplate.send(destination, new MessageCreator() {
                    public MapMessage createMessage(Session session) throws JMSException {
                        MapMessage mapMessage = session.createMapMessage();
                        mapMessage.setString("content", wxTemporaryQrcode.getContent());
                        mapMessage.setString("objectUuid", wxTemporaryQrcode.getObjectUuid());
                        mapMessage.setLong("scanId", wxTemporaryQrcode.getScanId());
                        mapMessage.setString("type", WxQrcodeTypeEnum.WX_QRCODE_TYPE_COMPANY_BIND_AUTO_SYSTEM.getText());

                        return mapMessage;
                    }});


                System.out.println("发送 了map 消息"+ type);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
*/




        @RequestMapping(value = "/qrcodeForCompany/{companyId}", method = RequestMethod.GET)
        public List<WxPermanentQrcode> qrcodeForCompany(@PathVariable("companyId") String companyId){


        Page<WxPermanentQrcode> wxPermanentQrcodes = wxGeneralRepository.findByObjectId(companyId,PageRequest.of(0,10));



        System.out.println("---------"+wxPermanentQrcodes.getSize());
        System.out.println("---------"+wxPermanentQrcodes.getContent().toString());
        return wxPermanentQrcodes.getContent();
    }







    @RequestMapping(method = RequestMethod.GET, value = "/permanent/{uuid}/{type}")
    public Map getPermanentQrcode(@PathVariable("uuid") String uuid,@PathVariable("type") String type,@RequestParam("info") String info) {

        WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(type);

        WxPermanentQrcode wxPermanentQrcode_cache = null;
        try {
            wxPermanentQrcode_cache = wxService.createPermanentQrcode(uuid,info,type_enum,
                    WeixinUrlFilte_delivery);
        } catch (Exception e) {
            e.printStackTrace();
        }


        Map map = new HashMap();

        map.put("content", wxPermanentQrcode_cache.getContent());
        map.put("uuid", uuid);

        map.put("id",wxPermanentQrcode_cache.getKey());
        return map;
    }




    @RequestMapping(method = RequestMethod.GET, value = "/temp/{uuid}/{type}")
    public Map getTempQrcode(@PathVariable("uuid") String uuid,@PathVariable("type") String type,@RequestParam("info") String info) {

            WxQrcodeTypeEnum type_enum = WxQrcodeTypeEnum.fromString(type);

        WxTemporaryQrcode wxPermanentQrcode_cache = tempQrcodeService.getMemoryTemporaryQrcode(uuid,info,type_enum,
                WeixinUrlFilte_delivery);


        Map map = new HashMap();

        map.put("content", wxPermanentQrcode_cache.getContent());
        map.put("uuid", uuid);

        map.put("id",wxPermanentQrcode_cache.getKey());
        return map;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/objectById/{id}")
    public Map getObjectByTempId(@PathVariable("id") Integer uuid) {


        WxTemporaryQrcode wxPermanentQrcode_cache = tempQrcodeService.getTempByKey(uuid);


        Map map = new HashMap();

        map.put("content", wxPermanentQrcode_cache.getContent());
        map.put("uuid", wxPermanentQrcode_cache.getObjectId());
        map.put("id",wxPermanentQrcode_cache.getKey());
        return map;
    }
}
