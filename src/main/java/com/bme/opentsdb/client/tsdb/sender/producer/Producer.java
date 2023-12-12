package com.bme.opentsdb.client.tsdb.sender.producer;

import com.bme.opentsdb.client.tsdb.bean.request.Point;

public interface Producer {

    /***
     * 写入队列
     * @param point 数据点
     */
    void send(Point point);

    /***
     * 关闭写入
     */
    void forbiddenSend();


}
