package com.bme.syx.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.Date;

public class SendMessageService {
    public static void main(String[] args) throws Exception {
        System.out.println("send message 100");
        //创建消息生产者，指定生产组名
        DefaultMQProducer defaultMQProducer = new DefaultMQProducer("syx-producer-group");
        //指定Nameserver的地址
        defaultMQProducer.setNamesrvAddr("127.0.0.1:9876");
        //启动生产者
        defaultMQProducer.start();
        long date = System.currentTimeMillis();

        //构建消息对象，主要是设置消息的主题、标签、内容
        Message message = new Message("syxtopic",("objectName:syx,objectvalue:宋玉新,objectTime:"+date).getBytes());

        //发送消息
        SendResult result = defaultMQProducer.send(message);


        System.out.println("SendResult-->"+result);
        //关闭生产者
        defaultMQProducer.shutdown();
    }
}