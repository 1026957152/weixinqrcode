package com.coalvalue.weixin.pojo;

/**
 * 微信开发者信息
 * 
 * @author sunlight
 *
 */
public class WechatDevInfo {
	private String appId;
	private String appSecret;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Override
	public String toString() {
		return "WechatDevInfo [appId=" + appId + ", appSecret=" + appSecret
				+ "]";
	}

}
