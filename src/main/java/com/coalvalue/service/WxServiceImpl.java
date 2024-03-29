package com.coalvalue.service;

import com.alibaba.fastjson.JSON;
import com.coalvalue.configuration.CommonConstant;

import com.coalvalue.domain.OperationResult;
import com.coalvalue.domain.entity.*;

import com.coalvalue.domain.enums.ResourceType;
import com.coalvalue.domain.enums.WxQrcodeStatusEnum;
import com.coalvalue.domain.enums.WxQrcodeTypeEnum;
import com.coalvalue.domain.json.WeixinQrcodeEventJson;

import com.coalvalue.repository.PermanentQrcodeRepository;
import com.coalvalue.repository.WxPermanentQrcodeRepository;
import com.coalvalue.repository.WxTemporaryQrcodeRepository;



import com.coalvalue.weixin.pojo.WeixinQRCode;

import com.coalvalue.weixin.util.AdvancedUtil;
import com.coalvalue.weixin.util.Constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by silence yuan on 2015/7/25.
 */

@Service("wxService")
public class WxServiceImpl extends BaseServiceImpl implements WxService {

    protected transient Logger logger = LoggerFactory.getLogger(getClass());    public static java.lang.String path_qrcode_image_resoure_path = "path_qrcode_image_resoure_path";
    @Autowired
    private WxPermanentQrcodeRepository wxPermanentQrcodeRepository;

    @Autowired
    private PermanentQrcodeRepository permanentQrcodeRepository;

    @Autowired
    private WxTemporaryQrcodeRepository wxTemporaryQrcodeRepository;

    @Autowired
    @Qualifier(value="kafkaTemplateJson")
    private KafkaTemplate kafkaTemplateJson;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private AccessTokenManagerService accessTokenManagerService;


    @Autowired
    private MySQLMaxValueIncrementer wxTemporaryQrcodeScanIdGenerator;
    @Autowired
    private MySQLMaxValueIncrementer wxPermanentQrcodeScanIdGenerator;








    @Override

    public WxPermanentQrcode getByCompany_type_new(String uuid, String resourceType, String type, String appId) {

        WxPermanentQrcode wxScanGeneral =  wxPermanentQrcodeRepository.findByObjectIdAndTypeAndAppIdAndStatus(uuid, type, appId, CommonConstant.QRCODE_STATUS_Valid);


        if(wxScanGeneral!= null){
           /* if(wxScanGeneral.getTicket()== null){

                String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();
                WeixinQRCode ticket = AdvancedUtil.createPermanentQRCode(accessToken, wxScanGeneral.getId());

                if (ticket.getErrorCode() == null) {

                    wxScanGeneral.setContent(ticket.getUrl());
                    wxScanGeneral.setTicket(ticket.getTicket());
                    wxScanGeneral = updateGetByCompany_type_new(wxScanGeneral);
                    return wxScanGeneral;

                }
            }*/
            return wxScanGeneral;
        }

        wxScanGeneral = new WxPermanentQrcode();


        wxScanGeneral.setObjectId(uuid);
        wxScanGeneral.setType(type);
        wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);
        wxScanGeneral.setAppId(appId);



        logger.debug("创建 新的永久二维马we are after find wxgeneral "+uuid);
        logger.debug("创建 新的永久二维马we are after find wxgeneral "+appId);
        logger.debug("创建 新的永久二维马we are after find wxgeneral ");


        //wxScanGeneral.setKey(wxScanGeneral.getId());
        wxScanGeneral = wxPermanentQrcodeRepository.save(wxScanGeneral);


        String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();
        WeixinQRCode ticket = AdvancedUtil.createPermanentQRCode(accessToken, wxScanGeneral.getId());

        if (ticket.getErrorCode() == null) {


           // wxScanGeneral.setContent(ticket.getUrl());
           // wxScanGeneral.setTicket(ticket.getTicket());
            wxScanGeneral = updateGetByCompany_type_new(wxScanGeneral);
            return wxScanGeneral;

        }

        return null;



    }
    public OperationResult getByCompany_type_new_RETURN(String uuid, String resourceType, String type, String appId) {

        WxPermanentQrcode wxScanGeneral =  wxPermanentQrcodeRepository.findByObjectIdAndTypeAndAppIdAndStatus(uuid, type, appId, CommonConstant.QRCODE_STATUS_Valid);

        Integer scanId = wxPermanentQrcodeScanIdGenerator.nextIntValue();

        if(wxScanGeneral != null && wxScanGeneral.getInfo() == null){
            Map map = new HashMap<>();

            map.put("companyId",uuid);
            wxScanGeneral.setInfo(JSON.toJSONString(map));
            wxScanGeneral = wxPermanentQrcodeRepository.save(wxScanGeneral);
        }
        if(wxScanGeneral== null){
            String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();
            WeixinQRCode ticket = AdvancedUtil.createPermanentQRCode(accessToken, scanId);

            if (ticket.getErrorCode() == null) {

                wxScanGeneral = new WxPermanentQrcode();


                wxScanGeneral.setObjectId(uuid);
                wxScanGeneral.setType(type);
                wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);
                wxScanGeneral.setAppId(appId);


                Map map = new HashMap<>();

                map.put("companyId",uuid);
                wxScanGeneral.setInfo(JSON.toJSONString(map));

                logger.debug("创建 新的永久二维马we are after find wxgeneral "+uuid);
                logger.debug("创建 新的永久二维马we are after find wxgeneral "+appId);
                logger.debug("创建 新的永久二维马we are after find wxgeneral ");


                //wxScanGeneral.setKey(scanId);
                wxScanGeneral = wxPermanentQrcodeRepository.save(wxScanGeneral);
              //  wxScanGeneral.setContent(ticket.getUrl());
               // wxScanGeneral.setTicket(ticket.getTicket());
                wxScanGeneral = updateGetByCompany_type_new(wxScanGeneral);



                WeixinQrcodeEventJson capacityEventJSON = new WeixinQrcodeEventJson();
                capacityEventJSON.setBehavior("Create");

                capacityEventJSON.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                capacityEventJSON.setAppId(wxScanGeneral.getAppId());
             //   capacityEventJSON.setScanId(wxScanGeneral.getKey());
                capacityEventJSON.setTtype(wxScanGeneral.getType());
                capacityEventJSON.setStatus(wxScanGeneral.getStatus());


                capacityEventJSON.setId(UUID.randomUUID().toString());


                capacityEventJSON.setUrl("_");
/*                capacityEventJSON.setContent(wxScanGeneral.getContent());
                capacityEventJSON.setTicket(wxScanGeneral.getTicket());*/
                capacityEventJSON.setTemporaryOrPermanent("Permanent");


                capacityEventJSON.setObjectUuid(wxScanGeneral.getObjectId());
              //  capacityEventJSON.setQrCode(wxScanGeneral.getQrCode());


                kafkaTemplateJson.send("weixin-qrcode-event-json",capacityEventJSON);



                return OperationResult.ok(wxScanGeneral);

            }else{
                return OperationResult.error("获取TICKET错误");
            }

        }

        return OperationResult.ok(wxScanGeneral);





    }


  //  @PostConstruct
    @Transactional
    public void init() {


        wxPermanentQrcodeRepository.findAll().stream().filter(e->e.getTappedStatus() == null).forEach(untappedQrcode ->{

            untappedQrcode.setTappedStatus(WxQrcodeStatusEnum.Untapped.getText());


            logger.debug("创建 新的永久二维马we are after find wxgeneral ");

            untappedQrcode = wxPermanentQrcodeRepository.save(untappedQrcode);
        });







    }

    @Override
    @Transactional
    public WxPermanentQrcode createPermanentQrcode(String uuid, String info, WxQrcodeTypeEnum type_enum, String appId) throws Exception {
        PermanentQrcode permanentQrcode = permanentQrcodeRepository.findTop1ByTappedStatusAndAppId(WxQrcodeStatusEnum.Untapped.getText(),appId);
             //   findByObjectIdAndTypeAndAppIdAndStatus(uuid, type_enum.getText(), appId, CommonConstant.QRCODE_STATUS_Valid);


        if(permanentQrcode!= null){

            WxPermanentQrcode untappedQrcode = new WxPermanentQrcode(); //wxPermanentQrcodeRepository.findTop1ByTappedStatus(WxQrcodeStatusEnum.Untapped.getText());
            untappedQrcode.setQrcodeId(permanentQrcode.getUuid());

            untappedQrcode.setObjectId(uuid);
            untappedQrcode.setAppId(appId);
            untappedQrcode.setInfo(info);
            untappedQrcode.setType(type_enum.getText());
            untappedQrcode.setTappedStatus(WxQrcodeStatusEnum.Tapped.getText());
            untappedQrcode.setContent(permanentQrcode.getContent());
            untappedQrcode.setStatus(CommonConstant.QRCODE_STATUS_Valid);
            untappedQrcode.setKey(permanentQrcode.getKey()+"");

            logger.debug("创建 新的永久二维马we are after find wxgeneral " + uuid);
            logger.debug("创建 新的永久二维马we are after find wxgeneral " + appId);
            logger.debug("创建 新的永久二维马we are after find wxgeneral ");

            untappedQrcode =  wxPermanentQrcodeRepository.save(untappedQrcode);


            permanentQrcode.setObjectId(uuid);
            permanentQrcode.setTappedStatus(WxQrcodeStatusEnum.Tapped.getText());
            permanentQrcode = permanentQrcodeRepository.save(permanentQrcode);

            return untappedQrcode;


        }else{
            return null;
        }






    }



    @Override
    @Transactional
    public WxPermanentQrcode createSelfQrcode( String key,String info, WxQrcodeTypeEnum scen_enum, String appId) throws Exception {



        System.out.println("为啥找不到呐，key info appid " + key+ "------"+ info+ "========="+ appId);

        appId = "wx6d7f2fec44663493";
        PermanentQrcode permanentQrcode = permanentQrcodeRepository.findTop1ByTappedStatusAndAppId(WxQrcodeStatusEnum.Untapped.getText(),appId);
        //   findByObjectIdAndTypeAndAppIdAndStatus(uuid, type_enum.getText(), appId, CommonConstant.QRCODE_STATUS_Valid);
        permanentQrcodeRepository.findAll().stream().forEach(e->{

            System.out.println(" key info appid " + e.getAppId()+ "------"+ e.getInfo()+ "========="+ e.getObjectId());

        });

        if(permanentQrcode!= null){
            permanentQrcode.setObjectId(key);
            permanentQrcode.setTappedStatus(WxQrcodeStatusEnum.Tapped.getText());
            permanentQrcode = permanentQrcodeRepository.save(permanentQrcode);





            WxPermanentQrcode untappedQrcode = wxPermanentQrcodeRepository.findByKeyAndScence(key,scen_enum.getText());

            if(untappedQrcode == null){
              untappedQrcode = new WxPermanentQrcode(); //wxPermanentQrcodeRepository.findTop1ByTappedStatus(WxQrcodeStatusEnum.Untapped.getText());
                untappedQrcode.setQrcodeId(permanentQrcode.getUuid());

                untappedQrcode.setObjectId(key);
                untappedQrcode.setAppId(appId);
                untappedQrcode.setInfo(info);
                untappedQrcode.setScence(scen_enum.getText());
                untappedQrcode.setTappedStatus(WxQrcodeStatusEnum.Tapped.getText());
                untappedQrcode.setContent(permanentQrcode.getContent());
                untappedQrcode.setStatus(CommonConstant.QRCODE_STATUS_Valid);
                untappedQrcode.setKey(key);

                logger.debug("创建 新的永久二维马we are after find wxgeneral " + key);
                logger.debug("创建 新的永久二维马we are after find wxgeneral " + appId);
                logger.debug("创建 新的永久二维马we are after find wxgeneral ");

                untappedQrcode =  wxPermanentQrcodeRepository.save(untappedQrcode);


            }else{

            }



            return untappedQrcode;


        }else{

            throw new Exception("没有找到可用的二维码");
        }






    }

    @Override
    @Transactional
    public WxTemporaryQrcode createTempQrcode(String uuid, String info, WxQrcodeTypeEnum type_enum, String appId) throws Exception {
        return null;
    }





    public WxPermanentQrcode prefetch(String uuid, String info, WxQrcodeTypeEnum type_enum, String appId) throws Exception {
        WxPermanentQrcode wxScanGeneral =  wxPermanentQrcodeRepository.findByObjectIdAndTypeAndAppIdAndStatus(uuid, type_enum.getText(), appId, WxQrcodeStatusEnum.Untapped.getText());

        Integer scanId = wxPermanentQrcodeScanIdGenerator.nextIntValue();

/*        if(wxScanGeneral != null && wxScanGeneral.getInfo() == null){
            Map map = new HashMap<>();
            map.put("companyId",uuid);
            wxScanGeneral.setInfo(JSON.toJSONString(map));
            wxScanGeneral = wxPermanentQrcodeRepository.save(wxScanGeneral);
        }*/
        if(wxScanGeneral== null){



            String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();
            WeixinQRCode ticket = AdvancedUtil.createPermanentQRCode(accessToken, scanId);

            if (ticket.getErrorCode() == null) {

                wxScanGeneral = new WxPermanentQrcode();


                wxScanGeneral.setObjectId(uuid);
                wxScanGeneral.setInfo(info);
                wxScanGeneral.setType(type_enum.getText());
                wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);
                wxScanGeneral.setAppId(appId);


                logger.debug("创建 新的永久二维马we are after find wxgeneral " + uuid);
                logger.debug("创建 新的永久二维马we are after find wxgeneral " + appId);
                logger.debug("创建 新的永久二维马we are after find wxgeneral ");

/*
                wxScanGeneral.setKey(scanId);
                wxScanGeneral = wxPermanentQrcodeRepository.save(wxScanGeneral);
                wxScanGeneral.setContent(ticket.getUrl());
                wxScanGeneral.setTicket(ticket.getTicket());
                wxScanGeneral = updateGetByCompany_type_new(wxScanGeneral);


                WeixinQrcodeEventJson capacityEventJSON = new WeixinQrcodeEventJson();
                capacityEventJSON.setBehavior("Create");

                capacityEventJSON.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                capacityEventJSON.setAppId(wxScanGeneral.getAppId());
                capacityEventJSON.setScanId(wxScanGeneral.getKey());
                capacityEventJSON.setTtype(wxScanGeneral.getType());
                capacityEventJSON.setStatus(wxScanGeneral.getStatus());


                capacityEventJSON.setId(UUID.randomUUID().toString());

                capacityEventJSON.setUrl("_");
                capacityEventJSON.setContent(wxScanGeneral.getContent());
                capacityEventJSON.setTicket(wxScanGeneral.getTicket());
                capacityEventJSON.setTemporaryOrPermanent("Permanent");


                capacityEventJSON.setObjectUuid(wxScanGeneral.getObjectId());
                capacityEventJSON.setQrCode(wxScanGeneral.getQrCode());


                kafkaTemplateJson.send("weixin-qrcode-event-json", capacityEventJSON);*/


                return wxScanGeneral;

            } else{
                throw new Exception("创建失败");
            }




        }else{
            return wxScanGeneral;
        }






    }





    @Transactional
    public WxPermanentQrcode updateGetByCompany_type_new(WxPermanentQrcode wxPermanentQrcode) {

        return wxPermanentQrcodeRepository.save(wxPermanentQrcode);
    }



    @Override
    @Transactional
    public WxTemporaryQrcode getByTimeSilence_type(String no, String type, String appId) {
        String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();


        Integer scanId = wxTemporaryQrcodeScanIdGenerator.nextIntValue();
        try {
            WeixinQRCode ticket = AdvancedUtil.createTemporaryQRCode(accessToken, 2592000, scanId);
            if (ticket != null) {
                String filePathToGraphsDir = servletContext.getRealPath("/files");//filePathToGraphsDir:D:\mei_0628\src\main\webapp\myfiles
                System.out.println(servletContext.getContextPath());

                logger.debug("filePathToGraphsDir:{} ", filePathToGraphsDir);

                return createTimeSilence_type(no,type,appId,scanId,ticket.getUrl(),ticket.getTicket());
            } else {
                logger.debug("===无法获得 更新了 accessTocken");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("失败");
        }
        return null;
    }
    @Transactional
    public WxTemporaryQrcode createTimeSilence_type(String uuid, String type, String appId, Integer scanId, String content, String ticket) {

        logger.debug("createTimeSilence_type {} {} {} {} {} {}",uuid,type,appId,scanId,content,ticket );


        WxTemporaryQrcode wxScanGeneral =  new WxTemporaryQrcode();
        wxScanGeneral.setObjectId(uuid);
        wxScanGeneral.setContent(content);
        wxScanGeneral.setTicket(ticket);
        wxScanGeneral.setType(type);
        wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);

        wxScanGeneral.setAppId(appId);

        wxScanGeneral = save(wxScanGeneral);
        wxScanGeneral.setKey(scanId);

        return wxTemporaryQrcodeRepository.save(wxScanGeneral);

    }

    public WxTemporaryQrcode createMemaryTimeSilence_type(String uuid, String type, String appId, Integer scanId, String content, String ticket) {

        logger.debug("createTimeSilence_type {} {} {} {} {} {}",uuid,type,appId,scanId,content,ticket );


        WxTemporaryQrcode wxScanGeneral =  new WxTemporaryQrcode();
        wxScanGeneral.setObjectId(uuid);
        wxScanGeneral.setContent(content);
        wxScanGeneral.setTicket(ticket);
        wxScanGeneral.setType(type);
        wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);

        wxScanGeneral.setAppId(appId);

        wxScanGeneral = save(wxScanGeneral);
        wxScanGeneral.setKey(scanId);

        return wxScanGeneral;

    }


    @Transactional
    public WxPermanentQrcode save(WxPermanentQrcode wxScanGeneral) {
        return wxPermanentQrcodeRepository.save(wxScanGeneral);
    }


    private WxTemporaryQrcode save(WxTemporaryQrcode wxTemporaryQrcode) {
        return wxTemporaryQrcodeRepository.save(wxTemporaryQrcode);
    }



    @Override
    public WxTemporaryQrcode getMemoryTemporaryQrcode(String no, String type, String appId) {
        String accessToken = accessTokenManagerService.getAccessToken(appId).getAccessToken();


        Integer scanId = wxTemporaryQrcodeScanIdGenerator.nextIntValue();
        try {
            WeixinQRCode ticket = AdvancedUtil.createTemporaryQRCode(accessToken, 2592000, scanId);
            if (ticket != null) {
                String filePathToGraphsDir = servletContext.getRealPath("/files");//filePathToGraphsDir:D:\mei_0628\src\main\webapp\myfiles
                System.out.println(servletContext.getContextPath());

                logger.debug("filePathToGraphsDir:{} ", filePathToGraphsDir);


                return createMemaryTimeSilence_type(no,type,appId,scanId,ticket.getUrl(),ticket.getTicket());
            } else {
                logger.debug("===无法获得 更新了 accessTocken");
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.debug("失败");
        }
        return null;
    }

    @Override
    @Transactional
    public WxTemporaryQrcode getTempByObjectId(String no, Integer id, String text, String wxQrcodeTypeScatteredOrder, int expireSeconds, int tryCount) {

        if(tryCount == 0){
            return null;
        }
        WxTemporaryQrcode wxScanGeneral =  wxTemporaryQrcodeRepository.findByTypeAndAppIdAndStatus(wxQrcodeTypeScatteredOrder, Constants.CORPID, CommonConstant.QRCODE_STATUS_Valid);



        logger.debug("we are after find wxgeneral ");

        if(wxScanGeneral != null /*&& wxScanGeneral.getQrCode() != null*/ ){

            return wxScanGeneral;
        }else {

            if(wxScanGeneral == null ){
                wxScanGeneral = new WxTemporaryQrcode();

                wxScanGeneral.setType(wxQrcodeTypeScatteredOrder);
  //              Integer scanId = sequenceGenerator.getScanId();
        //        wxScanGeneral.setScanId(scanId);

                wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);
                wxScanGeneral.setAppId(Constants.CORPID);
                wxScanGeneral.setExpireSeconds(expireSeconds);
                wxScanGeneral.setItemNo(no);
                //  if(wxQrcodeTypeScatteredOrder.equals(Constants.WX_QRCODE_TYPE_COMPANY)){
                //      wxScanGeneral.setSubScene(WeixinSubSceneEnum.COMPANY_SCAN_MAIN_AREA.getId());
                //  }
                //wxScanGeneral = saveTemporary(wxScanGeneral);
                wxScanGeneral =  wxTemporaryQrcodeRepository.save(wxScanGeneral);








            }

            if(wxScanGeneral.getTicket() == null ){
                Integer scanId = wxTemporaryQrcodeScanIdGenerator.nextIntValue();
                wxScanGeneral.setKey(scanId);
                //这边以下 就是 没有获得二维 码的 情况.
                String accessToken = accessTokenManagerService.getAccessToken("").getAccessToken();
                WeixinQRCode ticket = AdvancedUtil.createTemporaryQRCode(accessToken, expireSeconds, wxScanGeneral.getKey());
                if(ticket.getErrorCode() ==  null ){



 /*                   Configuration configuration = configurationRepository.findByKey(path_qrcode_image_resoure_path);
                    String filePathToGraphsDir = configuration.getStringValue();//servletContext.getRealPath("/files");

                    //  String filePathToGraphsDir = servletContext.getRealPath("/files");
                    System.out.println(servletContext.getContextPath());

                    logger.debug("filePathToGraphsDir:{} ", filePathToGraphsDir);
                    String path = AdvancedUtil.getQRCode(ticket.getTicket(), filePathToGraphsDir + "/company_qrcode_files" + File.separator);

                    System.out.println("---------------绝对地址： "+ (new File("/").getAbsolutePath()));
                    System.out.println("---------------FileUtil.BASE_DIR： "+ FileUtil.BASE_DIR);
                    System.out.println("---------------FileUtil.getWebAppDir： "+ FileUtil.getWebAppDir());
                    // System.out.println("---------------根目录所对应的绝对路径： "+ request.getServletPath());

                    //   wxScanGeneral.setScanId(scanId);
                    if(path != null){
                        wxScanGeneral.setQrCode(path.replace(filePathToGraphsDir,""));
                        wxScanGeneral.setContent(ticket.getUrl());
                        wxScanGeneral.setTicket(ticket.getTicket());
                        wxScanGeneral.setExpireSeconds(expireSeconds);
                        //return saveTemporary(wxScanGeneral);
                        return wxTemporaryQrcodeRepository.save(wxScanGeneral);


                    }else {
                        return null;
                    }*/


                 //   wxScanGeneral.setQrCode(path.replace(filePathToGraphsDir,""));
                    wxScanGeneral.setContent(ticket.getUrl());
                    wxScanGeneral.setTicket(ticket.getTicket());
                    wxScanGeneral.setExpireSeconds(expireSeconds);



                }else {
                    ticket.getErrorCode();
                    return getTempByObjectId(null, id, ResourceType.USER.getText(), wxQrcodeTypeScatteredOrder, expireSeconds,tryCount -1 );
                    //logger.debug("===无法获得 更新了 accessTocken");
                    // return null;
                }

            }

        }


        return wxScanGeneral;



    }


    public static class CreateTemporaryQrcodeForm{
        String corpId;
        Integer itemId;
        String itemType;
        String type;
        int expireSeconds;
        Integer ttryCountr;
        private String no;
        private String objectUuid;

        public String getCorpId() {
            return corpId;
        }

        public void setCorpId(String corpId) {
            this.corpId = corpId;
        }

        public Integer getItemId() {
            return itemId;
        }

        public void setItemId(Integer itemId) {
            this.itemId = itemId;
        }

        public String getItemType() {
            return itemType;
        }

        public void setItemType(String itemType) {
            this.itemType = itemType;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getExpireSeconds() {
            return expireSeconds;
        }

        public void setExpireSeconds(int expireSeconds) {
            this.expireSeconds = expireSeconds;
        }

        public Integer getTtryCountr() {
            return ttryCountr;
        }

        public void setTtryCountr(Integer ttryCountr) {
            this.ttryCountr = ttryCountr;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public void setObjectUuid(String objectUuid) {
            this.objectUuid = objectUuid;
        }

        public String getObjectUuid() {
            return objectUuid;
        }
    }


    @Override

    public WxTemporaryQrcode getTempByObjectId(CreateTemporaryQrcodeForm createTemporaryQrcodeForm){

        String objectUuid = createTemporaryQrcodeForm.getObjectUuid();


        String type = createTemporaryQrcodeForm.getType();
        int expireSeconds = createTemporaryQrcodeForm.getExpireSeconds();


        WxTemporaryQrcode wxScanGeneral =  wxTemporaryQrcodeRepository.findByObjectIdAndTypeAndAppIdAndStatus(objectUuid, type, createTemporaryQrcodeForm.corpId, CommonConstant.QRCODE_STATUS_Valid);

        if(wxScanGeneral == null ){
            Integer scanId = wxTemporaryQrcodeScanIdGenerator.nextIntValue();
            String accessToken = accessTokenManagerService.getAccessToken(createTemporaryQrcodeForm.corpId).getAccessToken();
            if(accessToken!= null){
                wxScanGeneral = new WxTemporaryQrcode();
                wxScanGeneral.setType(type);

                wxScanGeneral.setObjectId(objectUuid);
                wxScanGeneral.setStatus(CommonConstant.QRCODE_STATUS_Valid);
                wxScanGeneral.setAppId(createTemporaryQrcodeForm.corpId);
                wxScanGeneral.setExpireSeconds(expireSeconds);
                wxScanGeneral.setKey(scanId);

                WeixinQRCode ticket = AdvancedUtil.createTemporaryQRCode(accessToken, expireSeconds, scanId);


                if(ticket.getErrorCode() ==  null ){
                    wxScanGeneral.setContent(ticket.getUrl());
                    wxScanGeneral.setTicket(ticket.getTicket());
                    wxScanGeneral.setExpireSeconds(expireSeconds);
                    wxScanGeneral =  wxTemporaryQrcodeRepository.save(wxScanGeneral);
                }







                WeixinQrcodeEventJson capacityEventJSON = new WeixinQrcodeEventJson();
                capacityEventJSON.setBehavior("Create");

                capacityEventJSON.setTimestamp(LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli());
                capacityEventJSON.setAppId(wxScanGeneral.getAppId());
                capacityEventJSON.setScanId(wxScanGeneral.getKey());
                capacityEventJSON.setTtype(wxScanGeneral.getType());
                capacityEventJSON.setStatus(wxScanGeneral.getStatus());


                capacityEventJSON.setId(UUID.randomUUID().toString());

                capacityEventJSON.setSubScene(1);
                capacityEventJSON.setTemporaryOrPermanent("Temporary");
                capacityEventJSON.setUrl("_");
                capacityEventJSON.setContent(wxScanGeneral.getContent());
                capacityEventJSON.setTicket(wxScanGeneral.getTicket());

                capacityEventJSON.setType(wxScanGeneral.getType());
                capacityEventJSON.setObjectUuid(wxScanGeneral.getObjectId());
                capacityEventJSON.setQrCode(wxScanGeneral.getQrCode());


                kafkaTemplateJson.send("weixin-qrcode-event-json",capacityEventJSON);



            }


        }


        return wxScanGeneral;

    }
















}
