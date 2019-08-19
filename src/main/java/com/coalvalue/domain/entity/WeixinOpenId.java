package com.coalvalue.domain.entity;

import com.coalvalue.configuration.CommonConstant;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by yuan zhao  on 12/13/2015.
 */
@Entity
@Table(name = "weixin_open_id")
public class WeixinOpenId extends BaseDomain {


   // @OneToOne(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true)
/*    @OneToOne(optional = true, cascade = CascadeType.ALL)//,fetch = FetchType.LAZY)// mappedBy="user")
    @JoinColumn(name="user_id" ,referencedColumnName = "id", unique = true)
    private User user*/;
    @Column(name = "user_Id")

    private String userId;

    @Column(name = "open_id")
    private String openId;




    @Column(name = "status")
    private String status;


    // ��ע״̬��1�ǹ�ע��0��δ��ע����δ��עʱ��ȡ����������Ϣ
    private Integer subscribe;
    // �û���עʱ�䣬Ϊʱ���������û����ι�ע����ȡ����עʱ��
    private String subscribeTime;
    // �ǳ�
    private String nickname;
    // �û����Ա�1�����ԣ�2��Ů�ԣ�0��δ֪��
    private Integer sex;
    // �û����ڹ��
    private String country;
    // �û�����ʡ��
    private String province;
    // �û����ڳ���
    private String city;
    // �û������ԣ���������Ϊzh_CN
    private String language;
    // �û�ͷ��
    private String headImgUrl;

    private String probablyRole;
    private String groupId;

    private String appId;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getProbablyRole() {
        return probablyRole;
    }

    public void setProbablyRole(String probablyRole) {
        this.probablyRole = probablyRole;
    }

    @Column(name = "official_account_id")
    private String officialAccountId;

    public String getOfficialAccountId() {
        return officialAccountId;
    }

    public void setOfficialAccountId(String officialAccountId) {
        this.officialAccountId = officialAccountId;
    }

    public WeixinOpenId() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
/*    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }*/

    public String getOpenId() {
        return openId;
    }

    public String getStatus() {
        return status;
    }


    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Integer subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

    public boolean isBinded() {

        return userId!= null? true: false;

    }

    public boolean isSubscribed() {


        return status != null? status.equals(CommonConstant.STATUE_SUBSCRIBE):false;
    }

    public boolean isUnSubscribed() {
        return status != null? status.equals(CommonConstant.STATUE_UNSUBSCRIBE):false;

    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
