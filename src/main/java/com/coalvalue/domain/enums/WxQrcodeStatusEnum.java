package com.coalvalue.domain.enums;

import com.coalvalue.configuration.CommonConstant;
import com.coalvalue.domain.pojo.ListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by silence on 2016-07-12.
 */
public enum WxQrcodeStatusEnum {


    QRCODE_STATUS_Valid (CommonConstant.QRCODE_STATUS_Valid,"资产","A","",true,2592000),
    Untapped ("Untapped","负债","L","",true,2592000),

    Tapped ("Tapped","open_account_conduction","L","",true,2592000),

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

    private WxQrcodeStatusEnum(String statusText, String displayText, String id, String helpMessage, Boolean temporary, int expireSeconds) {
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
        for(WxQrcodeStatusEnum status : WxQrcodeStatusEnum.values()) {
            ListItem element = new ListItem(status.getText(), status.getDisplayText());
            if (status.getText().equals(statusText)){
                element.setSelected(true);
            }
            list.add(element);
        }
        return list;

    }
    public static WxQrcodeStatusEnum fromString(String text) {
        for (WxQrcodeStatusEnum status : WxQrcodeStatusEnum.values()) {
            if (status.getText().equals(text) ) {
                return status;
            }
        }
        System.out.println(" 找不到类型错误 text is :" + text);
        throw new RuntimeException("no customer status " + text);


    }
}
