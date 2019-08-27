package com.coalvalue.domain.enums;

import com.coalvalue.domain.pojo.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence on 2016-07-12.
 */
public enum WxQrcodeTypeEnum {


    WX_QRCODE_TYPE_BIND_EMPLOYEE_ACCOUNT ("bind_employee_account","资产","A","",true,2592000),
    WX_QRCODE_TYPE_DELIVERY_ORDER_DISTRRIBUTO ("delivery_order_distribute","负债","L","",true,2592000),
    WX_QRCODE_TYPE_CONSIGNEE_DELIVERY_ORDER ("consignee_get_delivery_order" ,"所有者权益","E","",true,2592000),
    WX_QRCODE_TYPE_COMPANY_BIND_AUTO_SYSTEM ("bind_auto_system" ,"所有者权益","E","",true,2592000),
    WX_QRCODE_TYPE_COMPANY_AUTO_SYSTEM_MANAGEMENT ("auto_system_management" ,"auto_system_management","E","",true,2592000),

    WX_QRCODE_TYPE_COMPANY_logistics ("company_logistics" ,"company_logistics","E","",false,2592000),
    WX_QRCODE_TYPE_COMPANY_delivery ("company_delivery" ,"company_delivery","E","",false,2592000),
    WX_QRCODE_TYPE_FOLLOW_COMPANY ("follow_company" ,"follow_company","E","",true,2592000),

    WX_QRCODE_TYPE_COMPANY ("company" ,"company","E","",true,2592000),
    WX_QRCODE_TYPE_COMPANY_TRADER ("company_trader" ,"company_trader","E","",true,2592000),
    WX_QRCODE_TYPE_CANVASSING ("canvassing" ,"canvassing","E","",true,2592000),
    WX_QRCODE_TYPE_COMPANY_coalpit ("company_coalpit" ,"company_coalpit","E","",true,2592000),

    WX_QRCODE_TYPE_CANVASSING_Conduction ("canvassing_conduction" ,"canvassing_conduction","E","",true,2592000),
    WX_QRCODE_TYPE_DELIVERY_ORDER_Conduction ("delivery_order_conduction","负债","L","",true,2592000),
    WX_QRCODE_TYPE_BIND_USER_ACCOUNT ("bind_user_account","bind_user_account","L","",true,2592000),
    WX_QRCODE_TYPE_STORAGESPACE ("storage","storage","L","",true,2592000),

    WX_QRCODE_TYPE_OPEN_ACCOUNT_Conduction ("open_account_conduction","open_account_conduction","L","",true,2592000),

    ;




    private final String statusText;

    private final String displayText;
    private final String id;

    private String helpMessage;
    private Boolean temporary;

    int expireSeconds;
    public String getHelpMessage() {
        return helpMessage;
    }

    public void setHelpMessage(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    public String getDisplayText() {
        return displayText;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    private WxQrcodeTypeEnum(String statusText, String displayText, String id, String helpMessage, Boolean temporary, int expireSeconds) {
        this.statusText = statusText;
        this.displayText = displayText;

        this.id = id;
        this.helpMessage = helpMessage;
        this.temporary = temporary;
        this.expireSeconds = expireSeconds;
    }
    public String getText() {
        return this.statusText;
    }

    public String getId() {
        return this.id;
    }





    public static List<ListItem> retriveTypese(String statusText) {

        List<ListItem> list = new ArrayList<ListItem>();
        for(WxQrcodeTypeEnum status : WxQrcodeTypeEnum.values()) {
            ListItem element = new ListItem(status.getText(), status.getDisplayText());
            if (status.getText().equals(statusText)){
                element.setSelected(true);
            }
            list.add(element);
        }
        return list;

    }
    public static WxQrcodeTypeEnum fromString(String text) {
        for (WxQrcodeTypeEnum status : WxQrcodeTypeEnum.values()) {
            if (status.getText().equals(text) ) {
                return status;
            }
        }
        System.out.println(" 找不到类型错误 text is :" + text);
        throw new RuntimeException("no customer status " + text);


    }
}
