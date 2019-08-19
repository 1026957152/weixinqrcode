package com.coalvalue.weixin.util;

import com.coalvalue.configuration.CommonConstant;

public class Constants {
	/**
	 * 常量说明：
	 * 此处定义的常量需要持久化，可以保存在数据库中，在需要的地方读取。
	 * 在多企业号中，最好以每个应用来定义。
     *     * 发送模板消息
     * appId 公众账号的唯一标识
     * appSecret 公众账号的密钥
     * openId 用户标识
	 */


	public static final int AGENTID = 1;
	public static final String TOKEN = "zhaoyuansb";
    public static final Integer ERROR_CODE_40001 = 40001;
    public static final Integer ERROR_CODE_40002 = 40002;

    public static final Integer ERROR_CODE_42001 = 42001;

    public static final Integer ERROR_CODE_45047 = 45047;
    public static final Integer ERROR_CODE_40003 = 40003;
    public static final String WX_TEAM_PURCHASING = "images/groupbuy.png";
    public static final String WX_distributor = "images/distributor.jpg";
    public static final String WX_logistics = "images/logistics.jpg";
    public static final String WEBSOCKET_USERNAME  ="WEBSOCKET_USERNAME";


    //	public static final String CORPID = "wxb1cc4b7deb393ead";
    public static  String CORPID = "";




    public static  String APP_SECRET = "";// "24dc8bd2a102e5f92acc8dda3c06b189";


    //测试
   //public static final String APP_SECRET = "24dc8bd2a102e5f92acc8dda3c06b189";


    public static  String encodingAESKey = null;// "WrvQ9ppFmtm18ZiQ0x297Xq0gLOX0eT0awXpltIZ6zI";

    public static  String HOST_WEBSITE = "http://c39b3f73.ngrok.io";
    public static  String HOST_WEBSITE_local = "http://192.168.1.5:8085";

    static {
        if(CommonConstant.TEST){
            CORPID = "wx58ef12e3befc3869"; //测试，开发
            APP_SECRET = "24dc8bd2a102e5f92acc8dda3c06b189";
            HOST_WEBSITE = HOST_WEBSITE;
        }else {
            CORPID = "wx242f2ca59a3973bf";// 生产系统
            APP_SECRET = "4431819030fbd391f89e5e9ad3325ce3";
            encodingAESKey = "WrvQ9ppFmtm18ZiQ0x297Xq0gLOX0eT0awXpltIZ6zI";
            HOST_WEBSITE = "http://yulinmei.cn";
        }
    }


  //  public static final String appSecret = "8b940571846ee307f0d03fc2be35418a";



    public static final String WORDS_OF_WELCOME = " \"感谢您关注【易找煤】\\n微信号：yizhaomei\";";

    public static final String STATUE_UNSUBSCRIBE = "UnSubScribe";
    public static final String STATUE_SUBSCRIBE = "SubScribe";

    //public static final String HOST_WEBSITE = "http://yizhaomei-test.ngrok.cc";

  //  public static final String HOST_WEBSITE = "http://a40c58b.ngrok.natapp.cn";




    public static final String HOST_WEBSITE_WITHOUT_P = CommonConstant.TEST_HOST;



    public static final String HOST_WEBSITE_LOGIN = "http://yulinmei.ngrok.natapp.cn/login";


    public static final int MAX_CONTEXT_NUM = 100;
    public static final int WX_COMPANY_SCENEID = 10000;
    public static final String WX_QRCODE_TYPE_COMPANY = "company";
    public static final String WX_QRCODE_TYPE_COMPANY_TRADER = "company_trader";
    public static final String WX_QRCODE_TYPE_COMPANY_LOGISTIC = "company_logistic";
    public static final String WX_QRCODE_TYPE_COMPANY_TIMESILCE = "time_silce";
    public static final String WX_QRCODE_TYPE_ACTIVATE_ACCOUNT_DRIVER = "activate driver account";
    public static final String WX_QRCODE_TYPE_ACTIVATE_ACCOUNT_MERCHANT = "activate merchant account" ;

    public static final String WX_QRCODE_TYPE_DELIVERY_ORDER_DISTRRIBUTO = "delivery_order_distribute";
    public static final String WX_QRCODE_TYPE_BIND_USER_ACCOUNT = "bind user account" ;
    public static final String WX_QRCODE_TYPE_BIND_DISTRIBUTOR_USER_ACCOUNT = "bind_distributor_user_account" ;
    public static final String LOCOAL_HOST = "http://localhost/";

    public static final String WX_QRCODE_TYPE_CAPACITY_APPLICATION = "capacity application";
    public static final String WX_QRCODE_TYPE_CAPACITY_SUPPLY = "capacity supply";
    public static final String WX_QRCODE_TYPE_CAPACITY_APPLICATION_ACTION_ARRIVAL = "capacity application arrival";
    public static final String WX_QRCODE_TYPE_CANVASSING = "canvassing";
    public static final String WX_QRCODE_TYPE_PROMOTION = "promotion";
    public static final String WX_QRCODE_TYPE_SCATTERED_ORDER = "scattred order";
    public static final String WX_QRCODE_TYPE_SHIPMENT_ACTION_SIGN_BY_CONSIGNEE = "sign by consignee";
    public static final String WX_QRCODE_TYPE_SHIPMENT = "shipment";
    public static final String WX_QRCODE_TYPE_ACCOUNT_ACTION_ACTIVE_COMPANY_USER = CommonConstant.QRCODE_ACCOUNT_ACTION_ACTIVE_COMPANY_USER;

    public static final String WX_QRCODE_TYPE_STORAGESPACE = "storageSpace";
    public static final String WX_QRCODE_TYPE_COAL_SUPPLY = "coalSupply";
    public static final String WX_QRCODE_TYPE_STORAGESPACE_Action_add_Counterman = "action add counterman";
    public static final String WX_QRCODE_TYPE_ROUTE = "route";

    public static final String WX_QRCODE_TYPE_transportOperation = "transportOperation";
    public static final String WX_QRCODE_TYPE_DISTRICT = "district";
    public static final String WX_QRCODE_TYPE_TOPIC = "topic";

    public static final String WX_QRCODE_TYPE_TEAM_DEAL = "team_deal";

    public static final String WX_QRCODE_TYPE_USER = "user";
    public static final String WX_QRCODE_TYPE_PRODUCT = "product";



    public static final String Current_Canvassing = "currentCanvassing";

    public static final String Current_Transport = "currentTransport";
    public static final String Current_Shipment = "currentShipment";

    public static String Current_Canvassing_approved = "currentTransportApproved";
    public static String Current_Transport_loading ="currentTransportLoading" ;

    public static String WX_QRCODE_TYPE_STORAGESPACE_queue = "storage_queue";
    public static String WX_QRCODE_TYPE_transportOperation_DELIVER_ORDER = "DELIVER_ORDER";

    public static String WX_QRCODE_TYPE_SHIPMENT_agreement =" shipment_agreement";
    public static String WX_QRCODE_TYPE_CustomerMemo = "CustomerMemo";


    public static String WX_QRCODE_TYPE_cooproduct = "coopproduct";
}
