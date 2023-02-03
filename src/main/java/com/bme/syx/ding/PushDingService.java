package com.bme.syx.ding;


import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.common.collect.Lists;
import com.taobao.api.ApiException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.List;

@Service
public class PushDingService {

    protected Logger logger = LoggerFactory.getLogger(PushDingService.class);

    @Autowired
    private PushDingMapper pushDingMapper;

    //@Scheduled(cron="0 */1 * * * ?")
    public void startPushDingDing(){
        logger.info("钉钉消息推送定时任务，---开始----");
        //查询所有发送消息
        List<PushDingEntity> pushDingEntitgList = pushDingMapper.selectAll();
        for (PushDingEntity pushDingEntity: pushDingEntitgList) {
            //调用发送消息方法
            pushDingDing(pushDingEntity);
        }
        logger.info("钉钉消息推送定时任务，---结束----");

    }

    public void pushDingDing(PushDingEntity pushDingEntity){
        logger.info("开始发送,群名称{}",pushDingEntity.getGroupName());
        Long timestamp = System.currentTimeMillis();
        //安全码签名
        String sign = getSign(timestamp,pushDingEntity.getDingSecret());
        //钉钉客户端
        DingTalkClient client = new DefaultDingTalkClient(pushDingEntity.getDingURL()+"&timestamp=" + timestamp + "&sign=" + sign);
        //@接收人手机号
        List<String> mobileList = Lists.newLinkedList();
        mobileList.add(pushDingEntity.getSendeen());
        OapiRobotSendRequest.At at = new OapiRobotSendRequest.At();
        at.setIsAtAll(false);
        at.setAtMobiles(mobileList);
        OapiRobotSendRequest request = new OapiRobotSendRequest();
        request.setAt(at);
        //消息内容
        String content = pushDingEntity.getMsg();
        logger.info("发送内容：{}",content);
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        request.setMsgtype("text");
        request.setText(text);
        OapiRobotSendResponse response = null;
        try {
            //发送
            response = client.execute(request);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        logger.info("发送完成,群名称{}",pushDingEntity.getGroupName());
    }

    //安全码签名
    public String getSign(Long timestamp,String dingSecret){
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
