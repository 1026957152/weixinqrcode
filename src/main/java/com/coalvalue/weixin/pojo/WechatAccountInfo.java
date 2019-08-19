package com.coalvalue.weixin.pojo;

/**
 * 微信账号基本信息
 * 
 * @author sunlight
 *
 */
public class WechatAccountInfo {
	/**
	 * 原始ID
	 */
	private String accountId;
	/**
	 * 名称
	 */
	private String accountName;
	/**
	 * 微信头像
	 */
	private String headImage;
	/**
	 * 微信号
	 */
	private String wechatNumber;
	/**
	 * 账号类型
	 */
	private String type;
	/**
	 * 认证情况
	 */
	private String authenticate;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getHeadImage() {
		return headImage;
	}

	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	public String getWechatNumber() {
		return wechatNumber;
	}

	public void setWechatNumber(String wechatNumber) {
		this.wechatNumber = wechatNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuthenticate() {
		return authenticate;
	}

	public void setAuthenticate(String authenticate) {
		this.authenticate = authenticate;
	}

	@Override
	public String toString() {
		return "WechatAccountInfo [accountId=" + accountId + ", accountName="
				+ accountName + ", headImage=" + headImage + ", wechatNumber="
				+ wechatNumber + ", type=" + type + ", authenticate="
				+ authenticate + "]";
	}

}
