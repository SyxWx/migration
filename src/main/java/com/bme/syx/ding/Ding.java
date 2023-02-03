package com.bme.syx.ding;

import com.alibaba.fastjson.JSON;
import com.aliyun.oss.common.utils.HttpUtil;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class Ding {


    //机器人URL
    public static String dingURL = "https://oapi.dingtalk.com/robot/send?access_token=40cb473604bfd71f0f41d9c72f30a07e3673bd6c896dcc7e961e8d8aa24572ee";
    //机器人密钥
    public static String dingSecret ="SECc1ad09c88bf977e2f81e53fd247cb2a8045ca0d41a9a86ad7a8cb8c867fece9f";
    //接收人
    public static String sendeen="18864835738";
    //消息内容
    public static String msg="测试:关于XX有组织数据接入情况，目前进度如何？";
    //间隔时间  s
    public static int sleeptime=50000;

    public static void main(String[] args) {


        for (int i = 0; i < 15; i++) {

            try {
                Thread.sleep(sleeptime);
                sendZhou(sleeptime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static  void sendZhou(int i){

        Long timeStamp = System.currentTimeMillis();
        String sign = getSign(timeStamp);

        DingTalkClient client = new DefaultDingTalkClient(dingURL+"&timestamp=" + timeStamp + "&sign=" + sign);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();

        boolean isAtAll = false;
        List<String> mobileList = Lists.newLinkedList();
        mobileList.add(sendeen);
        at.setIsAtAll(isAtAll);
        at.setAtMobiles(mobileList);
        request.setAt(at);
        String content = msg+i;
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setMsgtype("text");
        request.setText(text);
        OapiRobotSendResponse response = null;
        try {
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(response.getErrmsg());
    }

    public static String getSign(Long timestamp){
        try {
            String secret = dingSecret;
            String stringToSign = timestamp + "\n" + secret;
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
            byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
            String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), "UTF-8");
            return sign;
        }catch (Exception ex){
            return "";
        }
    }
}
