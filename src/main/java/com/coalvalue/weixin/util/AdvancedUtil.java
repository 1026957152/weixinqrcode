package com.coalvalue.weixin.util;

import com.coalvalue.weixin.pojo.*;
import com.coalvalue.weixin.pojo.resp.Article;
import com.coalvalue.weixin.pojo.resp.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 高级接口工具类
 *
 * @author liufeng
 * @date 2013-11-9
 */
public class AdvancedUtil {
    private static Logger log = LoggerFactory.getLogger(AdvancedUtil.class);

    /**
     * 组装文本客服消息
     *
     * @param openId 消息发送对象
     * @param content 文本消息内容
     * @return
     */
    public static String makeTextCustomMessage(String openId, String content) {
        // 对消息内容中的双引号进行转义
        content = content.replace("\"", "\\\"");
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%s\"}}";
        return String.format(jsonMsg, openId, content);
    }

    /**
     * 组装图片客服消息
     *
     * @param openId 消息发送对象
     * @param mediaId 媒体文件id
     * @return
     */
    public static String makeImageCustomMessage(String openId, String mediaId) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"%s\"}}";
        return String.format(jsonMsg, openId, mediaId);
    }

    /**
     * 组装语音客服消息
     *
     * @param openId 消息发送对象
     * @param mediaId 媒体文件id
     * @return
     */
    public static String makeVoiceCustomMessage(String openId, String mediaId) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"%s\"}}";
        return String.format(jsonMsg, openId, mediaId);
    }

    /**
     * 组装视频客服消息
     *
     * @param openId 消息发送对象
     * @param mediaId 媒体文件id
     * @param thumbMediaId 视频消息缩略图的媒体id
     * @return
     */
    public static String makeVideoCustomMessage(String openId, String mediaId, String thumbMediaId) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"%s\",\"thumb_media_id\":\"%s\"}}";
        return String.format(jsonMsg, openId, mediaId, thumbMediaId);
    }

    /**
     * 组装音乐客服消息
     *
     * @param openId 消息发送对象
     * @param music 音乐对象
     * @return
     */
    public static String makeMusicCustomMessage(String openId, Music music) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"music\",\"music\":%s}";
        jsonMsg = String.format(jsonMsg, openId, JSONObject.fromObject(music).toString());
        // 将jsonMsg中的thumbmediaid替换为thumb_media_id
        jsonMsg = jsonMsg.replace("thumbmediaid", "thumb_media_id");
        return jsonMsg;
    }

    /**
     * 组装图文客服消息
     *
     * @param openId 消息发送对象
     * @param articleList 图文消息列表
     * @return
     */
    public static String makeNewsCustomMessage(String openId, List<Article> articleList) {
        String jsonMsg = "{\"touser\":\"%s\",\"msgtype\":\"news\",\"news\":{\"articles\":%s}}";
        jsonMsg = String.format(jsonMsg, openId, JSONArray.fromObject(articleList).toString().replaceAll("\"", "\\\""));
        // 将jsonMsg中的picUrl替换为picurl
        jsonMsg = jsonMsg.replace("picUrl", "picurl");
        return jsonMsg;
    }

    /**
     * 发送客服消息
     *
     * @param accessToken 接口访问凭证
     * @param jsonMsg json格式的客服消息（包括touser、msgtype和消息内容）
     * @return true | false
     */
    public static boolean sendCustomMessage(String accessToken, String jsonMsg) {
        log.info("消息内容：{}", jsonMsg);
        boolean result = false;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 发送客服消息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
                log.info("客服消息发送成功 errcode:{} errmsg:{}", errorCode, errorMsg);
            } else {
                log.error("客服消息发送失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }

        return result;
    }

    public static JSONObject sendCustomMessageReturnJsonObject(String accessToken, String jsonMsg) {
        log.info("CustomMessa 消息内容：{}", jsonMsg);
        boolean result = false;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 发送客服消息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", jsonMsg);


        return jsonObject;
    }




    /**
     * 通过网页授权获取用户信息
     *
     * @param accessToken 网页授权接口调用凭证
     * @param openId 用户标识
     * @return SNSUserInfo
     */
    @SuppressWarnings( { "deprecation", "unchecked" })
    public static SNSUserInfo getSNSUserInfo(String accessToken, String openId) {
        SNSUserInfo snsUserInfo = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 通过网页授权获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                snsUserInfo = new SNSUserInfo();
                // 用户的标识
                snsUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                snsUserInfo.setNickname(jsonObject.getString("nickname"));
                // 性别（1是男性，2是女性，0是未知）
                snsUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                snsUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                snsUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                snsUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                snsUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
                // 用户特权信息
                snsUserInfo.setPrivilegeList(JSONArray.toList(jsonObject.getJSONArray("privilege"), List.class));
            } catch (Exception e) {
                snsUserInfo = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return snsUserInfo;
    }

    /**
     * 创建临时带参二维码
     *
     * @param accessToken 接口访问凭证
     * @param expireSeconds 二维码有效时间，单位为秒，最大不超过1800   最大不超过604800 7 天 30天（即2592000秒）
     * @param sceneId 场景ID
     * @return WeixinQRCode
     */
    public static WeixinQRCode createTemporaryQRCode(String accessToken, int expireSeconds, int sceneId) {
        WeixinQRCode weixinQRCode = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
        // 创建临时带参二维码
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));

        if (null != jsonObject) {
            try {
                weixinQRCode = new WeixinQRCode();
                weixinQRCode.setTicket(jsonObject.getString("ticket"));
                weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
                weixinQRCode.setUrl(jsonObject.getString("url"));
                log.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}", weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
            } catch (Exception e) {
                weixinQRCode = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinQRCode;
    }


    /**
     * 创建临时带参二维码
     *
     * @param accessToken 接口访问凭证
     * @param expireSeconds 二维码有效时间，单位为秒，最大不超过1800   最大不超过604800 7 天 30天（即2592000秒）
     * @param sceneId 场景ID
     * @return WeixinQRCode
     */
    public static WeixinQRCode createTemporaryQRCode__string_scene_id(String accessToken, int expireSeconds, String sceneId) {
        System.out.println("createTemporaryQRCode__string_scene_id" + sceneId);
        WeixinQRCode weixinQRCode = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonMsg = "{\"expire_seconds\": %d, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %s}}}";
        // 创建临时带参二维码
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, expireSeconds, sceneId));

        if (null != jsonObject) {
            try {
                weixinQRCode = new WeixinQRCode();
                weixinQRCode.setTicket(jsonObject.getString("ticket"));
                weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
                weixinQRCode.setUrl(jsonObject.getString("url"));
                log.info("创建临时带参二维码成功 ticket:{} expire_seconds:{}", weixinQRCode.getTicket(), weixinQRCode.getExpireSeconds());
            } catch (Exception e) {
                weixinQRCode = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("创建临时带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinQRCode;
    }

    /**
     * 创建永久带参二维码
     *
     * @param accessToken 接口访问凭证
     * @param sceneId 场景ID
     * @return ticket
     */
    public static WeixinQRCode createPermanentQRCode(String accessToken, int sceneId) {
        String ticket = null;
        WeixinQRCode weixinQRCode = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonMsg = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": %d}}}";
        // 创建永久带参二维码
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonMsg, sceneId));

        if (null != jsonObject) {
            try {

                weixinQRCode = new WeixinQRCode();
                weixinQRCode.setTicket(jsonObject.getString("ticket"));
                //weixinQRCode.setExpireSeconds(jsonObject.getInt("expire_seconds"));
                weixinQRCode.setUrl(jsonObject.getString("url"));
                ticket = jsonObject.getString("ticket");
                log.info("创建永久带参二维码成功 ticket:{}", ticket);
            } catch (Exception e) {
                e.printStackTrace();
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("创建永久带参二维码失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                throw e;
            }
        }

        //WeixinQRCode ticket
        return weixinQRCode;
    }

    /**
     * 根据ticket换取二维码
     *
     * @param ticket 二维码ticket
     * @param savePath 保存路径
     */
    public static String getQRCode(String ticket, String savePath) {
        String filePath = null;
        // 拼接请求地址
        String requestUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
        requestUrl = requestUrl.replace("TICKET", CommonUtil.urlEncodeUTF8(ticket));
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");
/*
           if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            if (!savePath.endsWith("\\")) {
                savePath += "\\";
            }*/
            // 将ticket作为文件名
            ticket = ticket.replace(System.getProperty("file.separator"),"");
            ticket = ticket.replace("/","");

            filePath = savePath + ticket + ".jpg";

            // 将微信服务器返回的输入流写入文件
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();
            log.info("根据ticket换取二维码成功，filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            log.error("根据ticket换取二维码失败：{}", e);
        }
        return filePath;
    }





    /**
     * 获取用户信息
     *
     * @param accessToken 接口访问凭证
     * @param openId 用户标识
     * @return WeixinUserInfo
     */
    public static WeixinUserInfo getUserInfo(String accessToken, String openId) {
        WeixinUserInfo weixinUserInfo = null;
        System.out.print("获取 UserInf 详情");
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("OPENID", openId);
        // 获取用户信息
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                weixinUserInfo = new WeixinUserInfo();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 关注状态（1是关注，0是未关注），未关注时获取不到其余信息
                weixinUserInfo.setSubscribe(jsonObject.getInt("subscribe"));
                // 用户关注时间
                weixinUserInfo.setSubscribeTime(jsonObject.getString("subscribe_time"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户的语言，简体中文为zh_CN
                weixinUserInfo.setLanguage(jsonObject.getString("language"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode, errorMsg);
                }
            }
        }
        return weixinUserInfo;
    }

    /**
     * 获取关注者列表
     *
     * @param accessToken 调用接口凭证
     * @param nextOpenId 第一个拉取的openId，不填默认从头开始拉取
     * @return WeixinUserList
     */
    @SuppressWarnings( { "unchecked", "deprecation" })
    public static WeixinUserList getUserList(String accessToken, String nextOpenId) {
        WeixinUserList weixinUserList = null;

        if (null == nextOpenId)
            nextOpenId = "";

        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("NEXT_OPENID", nextOpenId);
        // 获取关注者列表
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
        // 如果请求成功
        if (null != jsonObject) {
            try {
                weixinUserList = new WeixinUserList();
                weixinUserList.setTotal(jsonObject.getInt("total"));
                weixinUserList.setCount(jsonObject.getInt("count"));
                weixinUserList.setNextOpenId(jsonObject.getString("next_openid"));
                JSONObject dataObject = (JSONObject) jsonObject.get("data");
                weixinUserList.setOpenIdList(JSONArray.toList(dataObject.getJSONArray("openid"), List.class));
            } catch (Exception e) {
                weixinUserList = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("获取关注者列表失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinUserList;
    }

    /**
     * 查询分组
     *
     * @param accessToken 调用接口凭证
     */
    @SuppressWarnings( { "unchecked", "deprecation" })
    public static List<WeixinGroup> getGroups(String accessToken) {
        List<WeixinGroup> weixinGroupList = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 查询分组
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);

        if (null != jsonObject) {
            try {
                weixinGroupList = JSONArray.toList(jsonObject.getJSONArray("groups"), WeixinGroup.class);
            } catch (Exception e) {
                weixinGroupList = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("查询分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinGroupList;
    }

    /**
     * 创建分组
     *
     * @param accessToken 接口访问凭证
     * @param groupName 分组名称
     * @return
     */
    public static WeixinGroup createGroup(String accessToken, String groupName) {
        WeixinGroup weixinGroup = null;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonData = "{\"group\" : {\"name\" : \"%s\"}}";
        // 创建分组
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, groupName));

        if (null != jsonObject) {
            try {
                weixinGroup = new WeixinGroup();
                weixinGroup.setId(jsonObject.getJSONObject("group").getInt("id"));
                weixinGroup.setName(jsonObject.getJSONObject("group").getString("name"));
            } catch (Exception e) {
                weixinGroup = null;
                int errorCode = jsonObject.getInt("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                log.error("创建分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return weixinGroup;
    }

    /**
     * 修改分组名
     *
     * @param accessToken 接口访问凭证
     * @param groupId 分组id
     * @param groupName 修改后的分组名
     * @return true | false
     */
    public static boolean updateGroup(String accessToken, int groupId, String groupName) {
        boolean result = false;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonData = "{\"group\": {\"id\": %d, \"name\": \"%s\"}}";
        // 修改分组名
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, groupId, groupName));

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
                log.info("修改分组名成功 errcode:{} errmsg:{}", errorCode, errorMsg);
            } else {
                log.error("修改分组名失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }

    /**
     * 移动用户分组
     *
     * @param accessToken 接口访问凭证
     * @param openId 用户标识
     * @param groupId 分组id
     * @return true | false
     */
    public static boolean updateMemberGroup(String accessToken, String openId, int groupId) {
        boolean result = false;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonData = "{\"openid\":\"%s\",\"to_groupid\":%d}";
        // 移动用户分组
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, openId, groupId));

        if (null != jsonObject) {
            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
                log.info("移动用户分组成功 errcode:{} errmsg:{}", errorCode, errorMsg);
            } else {
                log.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }


    /**
     * 测试个性化菜单匹配结果
     *
     * @param accessToken 接口访问凭证
     * @param openId 用户标识
     * @param groupId 分组id
     * @return true | false
     */
    public static boolean checkMemberMenuConditional(String accessToken, String openId, int groupId) {
        boolean result = false;
        // 拼接请求地址
        String requestUrl = "https://api.weixin.qq.com/cgi-bin/menu/trymatch?access_token=ACCESS_TOKEN";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken);
        // 需要提交的json数据
        String jsonData = "{\"user_id\":\"%s\",\"to_groupid\":%d}";
        // 移动用户分组
        JSONObject jsonObject = CommonUtil.httpsRequest(requestUrl, "POST", String.format(jsonData, openId, groupId));

        if (null != jsonObject) {
            log.info("result:{}", jsonObject.toString());

            int errorCode = jsonObject.getInt("errcode");
            String errorMsg = jsonObject.getString("errmsg");
            if (0 == errorCode) {
                result = true;
                log.info("查询成功 errcode:{} errmsg:{}\n", errorCode, errorMsg);
                log.info("result:{}", jsonObject.toString());

            } else {
                log.error("移动用户分组失败 errcode:{} errmsg:{}", errorCode, errorMsg);
            }
        }
        return result;
    }

    /**
     * 文件上传到微信服务器
     * @param fileType 文件类型
     * @param filePath 文件路径
     * @return JSONObject
     * @throws Exception
     */
    public  static WeixinMedia  send(String accessToken,String fileType, String filePath) throws Exception {
        String result = null;
        File file = new File(filePath);
        if (!file.exists() || !file.isFile()) {
            throw new IOException("文件不存在");
        }
        /**
         * 第一部分
         */
        URL urlObj = new URL("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+ accessToken + "&type="+fileType+"");
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式
        con.setDoInput(true);
        con.setDoOutput(true);
        con.setUseCaches(false); // post方式不能使用缓存
        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"file\";filename=\""+ file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);
        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                buffer.append(line);
            }
            if(result==null){
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
            throw new IOException("数据读取异常");
        } finally {
            if(reader!=null){
                reader.close();
            }
        }
        JSONObject jsonObj =JSONObject.fromObject(result);//new JSONObject(result);
        System.out.println(" jsonObject string is :"+ jsonObj.toString());
        WeixinMedia weixinMedia = new WeixinMedia();
        weixinMedia.setType(jsonObj.getString("type"));
        // type等于thumb时的返回结果和其它类型不一样
        //if ("thumb".equals(type))
          //  weixinMedia.setMediaId(jsonObj.getString("thumb_media_id"));
        //else
            weixinMedia.setMediaId(jsonObj.getString("media_id"));
        weixinMedia.setCreatedAt(jsonObj.getInt("created_at"));

    return weixinMedia;

    }

    /**
     * 上传媒体文件
     *
     * @param accessToken 接口访问凭证
     * @param type 媒体文件类型（image、voice、video和thumb）
     * @param mediaFileUrl 媒体文件的url
     */
    public static WeixinMedia uploadMedia(String accessToken, String type, String mediaFileUrl) {
        WeixinMedia weixinMedia = null;
        // 拼装请求地址
        String uploadMediaUrl = "http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
        uploadMediaUrl = uploadMediaUrl.replace("ACCESS_TOKEN", accessToken).replace("TYPE", type);

        // 定义数据分隔符
        String boundary = "------------"+ System.currentTimeMillis();
        try {
            URL uploadUrl = new URL(uploadMediaUrl);
            HttpURLConnection uploadConn = (HttpURLConnection) uploadUrl.openConnection();
            uploadConn.setDoOutput(true);
            uploadConn.setDoInput(true);
            uploadConn.setRequestMethod("POST");

            // 设置请求头Content-Type
            uploadConn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
            // 获取媒体文件上传的输出流（往微信服务器写数据）
            OutputStream outputStream = uploadConn.getOutputStream();

            URL mediaUrl = new URL(mediaFileUrl);
            HttpURLConnection meidaConn = (HttpURLConnection) mediaUrl.openConnection();
            meidaConn.setDoOutput(true);
            meidaConn.setRequestMethod("GET");

            // 从请求头中获取内容类型
            String contentType = "application/octet-stream";//meidaConn.getHeaderField("Content-Type");
            System.out.print("----------------contentType :  " + contentType);
            // 根据内容类型判断文件扩展名
            String fileExt =".mp3";// CommonUtil.getFileExt(contentType);
            System.out.print("----------------根据内容类型判断文件扩展名 :  " + fileExt);

            // 请求体开始
            outputStream.write(("--" + boundary + "\r\n").getBytes());
            outputStream.write(String.format("Content-Disposition: form-data; name=\"media\"; filename=\"file1%s\"\r\n", fileExt).getBytes());
            outputStream.write(String.format("Content-Type: %s\r\n\r\n", contentType).getBytes());

            // 获取媒体文件的输入流（读取文件）
            BufferedInputStream bis = new BufferedInputStream(meidaConn.getInputStream());
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1) {
                // 将媒体文件写到输出流（往微信服务器写数据）
                outputStream.write(buf, 0, size);
            }
            // 请求体结束
            outputStream.write(("\r\n--" + boundary + "--\r\n").getBytes());
            outputStream.close();
            bis.close();
            meidaConn.disconnect();

            // 获取媒体文件上传的输入流（从微信服务器读数据）
            InputStream inputStream = uploadConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            uploadConn.disconnect();

            // 使用JSON-lib解析返回结果
            JSONObject jsonObject = JSONObject.fromObject(buffer.toString());

            System.out.println(" jsonObject string is :"+ jsonObject.toString());
            weixinMedia = new WeixinMedia();
            weixinMedia.setType(jsonObject.getString("type"));
            // type等于thumb时的返回结果和其它类型不一样
            if ("thumb".equals(type))
                weixinMedia.setMediaId(jsonObject.getString("thumb_media_id"));
            else
                weixinMedia.setMediaId(jsonObject.getString("media_id"));
            weixinMedia.setCreatedAt(jsonObject.getInt("created_at"));
        } catch (Exception e) {
            weixinMedia = null;
            log.error("上传媒体文件失败：{}", e);
        }
        return weixinMedia;
    }

    /**
     * 下载媒体文件
     *
     * @param accessToken 接口访问凭证
     * @param mediaId 媒体文件标识
     * @param savePath 文件在服务器上的存储路径
     * @return
     */
    public static String getMedia(String accessToken, String mediaId, String savePath) {
        String filePath = null;
        String relativePath = null;
        // 拼接请求地址
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        System.out.println(requestUrl);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;

            relativePath = "/files/download/"+ mediaId + fileExt;




            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();
            log.info("下载媒体文件成功，filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            log.error("下载媒体文件失败：{}", e);
        }
        return relativePath;
    }




    /**
     * 下载媒体文件
     *
     * @param accessToken 接口访问凭证
     * @param mediaId 媒体文件标识
     * @param savePath 文件在服务器上的存储路径
     * @return
     */
    public static String getTransferCustomerServiceMedia(String accessToken, String mediaId, String savePath) {
        String filePath = null;
        String relativePath = null;
        // 拼接请求地址
        String ul = "https://api.weixin.qq.com/customservice/kfaccount/add?access_token=my_token";
        String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
        requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
        System.out.println(requestUrl);
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoInput(true);
            conn.setRequestMethod("GET");

            if (!savePath.endsWith("/")) {
                savePath += "/";
            }
            // 根据内容类型获取扩展名
            String fileExt = CommonUtil.getFileExt(conn.getHeaderField("Content-Type"));
            // 将mediaId作为文件名
            filePath = savePath + mediaId + fileExt;

            relativePath = "/files/download/"+ mediaId + fileExt;




            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            byte[] buf = new byte[8096];
            int size = 0;
            while ((size = bis.read(buf)) != -1)
                fos.write(buf, 0, size);
            fos.close();
            bis.close();

            conn.disconnect();
            log.info("下载媒体文件成功，filePath=" + filePath);
        } catch (Exception e) {
            filePath = null;
            log.error("下载媒体文件失败：{}", e);
        }
        return relativePath;
    }

    public static void main_backup(String args[]) {
        // 获取接口访问凭证
        String accessToken = CommonUtil.getToken(Constants.CORPID, Constants.APP_SECRET).getAccessToken();

        /**
         * 发送客服消息（文本消息）
         */
        // 组装文本客服消息
        String jsonTextMsg = makeTextCustomMessage("oEdzejiHCDqafJbz4WNJtWTMbDcE", "点击查看<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>");
        // 发送客服消息
        sendCustomMessage(accessToken, jsonTextMsg);

        /**
         * 发送客服消息（图文消息）
         */
        Article article1 = new Article();
        article1.setTitle("微信上也能斗地主");
        article1.setDescription("");
        article1.setPicUrl("http://www.egouji.com/xiaoq/game/doudizhu_big.png");
        article1.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Doudizhu/doudizhu.htm");
        Article article2 = new Article();
        article2.setTitle("傲气雄鹰\n80后不得不玩的经典游戏");
        article2.setDescription("");
        article2.setPicUrl("http://www.egouji.com/xiaoq/game/aoqixiongying.png");
        article2.setUrl("http://resource.duopao.com/duopao/games/small_games/weixingame/Plane/aoqixiongying.html");
        List<Article> list = new ArrayList<Article>();
        list.add(article1);
        list.add(article2);
        // 组装图文客服消息
        String jsonNewsMsg = makeNewsCustomMessage("oJVbUvoDG4bF8n049N71EhFQDKp0", list);
        // 发送客服消息
        sendCustomMessage(accessToken, jsonNewsMsg);

        /**
         * 创建临时二维码
         */
        WeixinQRCode weixinQRCode = createTemporaryQRCode(accessToken, 900, 111111);
        // 临时二维码的ticket
        System.out.println(weixinQRCode.getTicket());
        // 临时二维码的有效时间
        System.out.println(weixinQRCode.getExpireSeconds());

        /**
         * 根据ticket换取二维码
         */
        String ticket = "gQEg7zoAAAAAAAAAASxodHRwOi8vd2VpeGluLnFxLmNvbS9xL2lIVVJ3VmJsTzFsQ0ZuQ0Y1bG5WAAIEW35+UgMEAAAAAA==";
        String savePath = "G:/download";
        // 根据ticket换取二维码
        getQRCode(ticket, savePath);

        /**
         * 获取用户信息
         */
        WeixinUserInfo user = getUserInfo(accessToken, "oJVbUvoDG4bF8n049N71EhFQDKp0");
        System.out.println("OpenID：" + user.getOpenId());
        System.out.println("关注状态：" + user.getSubscribe());
        System.out.println("关注时间：" + user.getSubscribeTime());
        System.out.println("昵称：" + user.getNickname());
        System.out.println("性别：" + user.getSex());
        System.out.println("国家：" + user.getCountry());
        System.out.println("省份：" + user.getProvince());
        System.out.println("城市：" + user.getCity());
        System.out.println("语言：" + user.getLanguage());
        System.out.println("头像：" + user.getHeadImgUrl());

        /**
         * 获取关注者列表
         */
        WeixinUserList weixinUserList = getUserList(accessToken, "");
        System.out.println("总关注用户数：" + weixinUserList.getTotal());
        System.out.println("本次获取用户数：" + weixinUserList.getCount());
        System.out.println("OpenID列表：" + weixinUserList.getOpenIdList().toString());
        System.out.println("next_openid：" + weixinUserList.getNextOpenId());

        /**
         * 查询分组
         */
        List<WeixinGroup> groupList = getGroups(accessToken);
        // 循环输出各分组信息
        for (WeixinGroup group : groupList) {
            System.out.println(String.format("ID：%d 名称：%s 用户数：%d", group.getId(), group.getName(), group.getCount()));
        }

        /**
         * 创建分组
         */
        WeixinGroup group = createGroup(accessToken, "公司员工");
        System.out.println(String.format("成功创建分组：%s id：%d", group.getName(), group.getId()));

        /**
         * 修改分组名
         */
        updateGroup(accessToken, 100, "同事");

        /**
         * 移动用户分组
         */
        updateMemberGroup(accessToken, "oJVbUvoDG4bF8n049N71EhFQDKp0", 100);

        /**
         * 上传多媒体文件
         */
        WeixinMedia weixinMedia = uploadMedia(accessToken, "voice", "http://localhost:8080/weixinmpapi/test.mp3");
        System.out.println(weixinMedia.getMediaId());
        System.out.println(weixinMedia.getType());
        System.out.println(weixinMedia.getCreatedAt());

        /**
         * 下载多媒体文件
         */
        getMedia(accessToken, "N7xWhOGYSLWUMPzVcGnxKFbhXeD_lLT5sXxyxDGEsCzWIB2CcUijSeQOYjWLMpcn", "G:/download");
    }



    public static void main(String[] args) {
        // 第三方用户唯一凭证
        String appId = Constants.CORPID;
        // 第三方用户唯一凭证密钥
        String appSecret =Constants.APP_SECRET;// "APPSECRET";

        // 调用接口获取凭证
        Token accessToken = CommonUtil.getToken(appId, appSecret);
        WeixinQRCode ticket = AdvancedUtil.createPermanentQRCode(accessToken.getAccessToken(), Constants.WX_COMPANY_SCENEID);

        AdvancedUtil.getQRCode(ticket.getTicket(), "d:/");

    }

}
