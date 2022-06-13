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


    public static void main(String[] args) {


        for (int i = 0; i < 15; i++) {


            try {
                Thread.sleep(10000);
                sendZhou(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }




    public static  void sendZhou(int i){

        Long timeStamp = System.currentTimeMillis();
        String sign = getSign(timeStamp);

        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?access_token=f29d15561ab3a2bac1b8dbf1bda6bb424a73c3abb66a8d95d0f7fda47f858096&timestamp=" + timeStamp + "&sign=" + sign);



        OapiRobotSendRequest request = new OapiRobotSendRequest();
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();

        boolean isAtAll = false;
        List<String> mobileList = Lists.newLinkedList();
        mobileList.add("18864835738");

        at.setIsAtAll(isAtAll);
        at.setAtMobiles(mobileList);
        request.setAt(at);


        String content = "我是周广为"+i;
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();

        content = "测试"+content;
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
            String secret = "SECe262bb48f25554a29fb4e3004c08804b047a0e618597bd5b18bb3058ebc7d2ba";
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



    public static String bulidReqStr(String content, boolean isAtAll,List<String> mobileList){
        //消息内容
        Map<String, String> contentMap = Maps.newHashMap();
        contentMap.put("content", content);

        //通知人
        Map<String, Object> atMap = Maps.newHashMap();
        //1.是否通知所有人
        atMap.put("isAtAll", isAtAll);
        //2.通知具体人的手机号码列表
        atMap.put("atMobiles", mobileList);

        Map<String, Object> reqMap = Maps.newHashMap();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSON.toJSONString(reqMap);

    }
}
