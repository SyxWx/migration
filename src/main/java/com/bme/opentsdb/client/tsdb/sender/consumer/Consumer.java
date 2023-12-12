package com.bme.opentsdb.client.tsdb.sender.consumer;

public interface Consumer {

    /***
     * 开始消费，启动线程池中的消费线程
     */
    void start();

    /***
     * 停止消费，会等待线程池中的任务完成
     */
    void gracefulStop();

    /***
     * 强制停止
     */
    void forceStop();

}
