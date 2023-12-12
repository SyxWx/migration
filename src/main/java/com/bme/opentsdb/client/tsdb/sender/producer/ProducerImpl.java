package com.bme.opentsdb.client.tsdb.sender.producer;

import com.bme.opentsdb.client.tsdb.bean.request.Point;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class ProducerImpl implements Producer{
    /**
     * 消息队列
     */
    private final BlockingQueue<Point> queue;

    /**
     * 是否禁止写入队列
     */
    private final AtomicBoolean forbiddenWrite = new AtomicBoolean(false);

    public ProducerImpl(BlockingQueue<Point> queue) {
        this.queue = queue;
        log.debug("the producer has started!");
    }

    @Override
    public void send(Point point) {
        if (forbiddenWrite.get()) {
            throw new IllegalStateException("producer has been forbidden write.");
        }
        try {
            // 队列满时，put方法会被阻塞ProducerImpl
            queue.put(point);
        } catch (InterruptedException e) {
            log.error("producer put queue Interrupted.", e);
        }
    }

    @Override
    public void forbiddenSend() {
        forbiddenWrite.compareAndSet(false, true);
    }
}
