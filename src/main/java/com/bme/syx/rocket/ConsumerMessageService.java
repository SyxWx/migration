package com.bme.syx.rocket;

import com.alibaba.fastjson.JSONObject;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;

public class ConsumerMessageService {

    public static void main(String[] args) throws  Exception {

        //消费者的组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("syx-product-group");
        //指定NameServer地址，多个地址 ; 隔开
        consumer.setNamesrvAddr("127.0.0.1:9876");

        //设置consumer所订阅的Topic和Tag，*代表全部的Tag
        consumer.subscribe("syxtopic","*");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
        consumer.registerMessageListener(
                (MessageListenerConcurrently) (list,contexy)->{
                    try {
                        //遍历mq信息
                        for(MessageExt messageExt : list){
                            //接取mq中body的信息
                            String messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                            /**
                             * 了解到上游推送格式是JSON 所有的转换为JSON
                             * JSONObject jsonObject = JSONObject.parseObject(messageBody);
                             */
                            System.out.println("本次收到的数据如："+messageBody);
                            /**
                             * 业务处理
                             *
                             * service.exe(messageBody);
                             */
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return ConsumeConcurrentlyStatus.RECONSUME_LATER;// 稍后再试
                    }
                    return  ConsumeConcurrentlyStatus.CONSUME_SUCCESS;//消费成功
                });
        consumer.start();
    }
}
