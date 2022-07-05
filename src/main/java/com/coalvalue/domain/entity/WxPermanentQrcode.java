package com.coalvalue.domain.entity;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by silence on 2016/3/18.
 */
@Entity
@Table(name = "wx_permanent_qrcode")

public class WxPermanentQrcode extends BaseDomain{



    @Column(name = "app_id")
    private String appId;
    private String status;
    @Column(name = "tapped_status")
    private String tappedStatus;
    private String qrcodeId;
    private String type;
    private String objectId;
    private String info;
    private String content;
    @Column(name = "key_")

    private String key;
    private String scence;


    public String getTappedStatus() {
        return tappedStatus;
    }

    public void setTappedStatus(String tappedStatus) {
        this.tappedStatus = tappedStatus;
    }









    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }





    public String getObjectId() {

        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setQrcodeId(String qrcodeId) {
        this.qrcodeId = qrcodeId;
    }

    public String getQrcodeId() {
        return qrcodeId;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public void setScence(String scence) {
        this.scence = scence;
    }

    public String getScence() {
        return scence;
    }
}
