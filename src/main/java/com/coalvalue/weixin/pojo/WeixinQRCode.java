package com.coalvalue.weixin.pojo;

/**
 * ��ʱ��ά����Ϣ
 *
 * @author liufeng
 * @date 2013-11-10
 */
public class WeixinQRCode {
    // ��ȡ�Ķ�ά��ticket
    private String ticket;
    // ��ά�����Чʱ�䣬��λΪ�룬��󲻳���1800
    private int expireSeconds;
    private String url;
    private Integer errorCode;

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }
}
