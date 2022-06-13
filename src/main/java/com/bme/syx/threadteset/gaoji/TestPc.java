package com.bme.syx.threadteset.gaoji;

import lombok.SneakyThrows;
import org.elasticsearch.common.recycler.Recycler;

public class TestPc {


    public static void main(String[] args) {

        SynContainer synContainer = new SynContainer();
        new Productor(synContainer).start();
        new Consumer(synContainer).start();



    }
}


// 生产者
class Productor extends Thread{
    SynContainer container;
    public Productor(SynContainer container){
        this.container = container;
    }
    //生产
    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            System.out.println("生产了-->"+i+"只鸡");
            container.push(new Chicken(i));
        }
    }
}

//消费者
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container = container;
    }

    //消费

    @SneakyThrows
    @Override
    public void run() {
        for (int i = 1; i < 101; i++) {
            Chicken chicken = container.pop();
            System.out.println("消费了-->"+chicken.id+"只鸡");

        }

    }
}

//产品
class Chicken{
    int id;
    public Chicken(int id) {
        this.id = id;
    }
}

// 消息缓存区
class SynContainer{
    //需要一个容器大小
    Chicken[] chickens = new Chicken[10];
    int count=0;

    //生产者放入产品
    public synchronized void  push(Chicken chicken) throws InterruptedException {
        //如果容器满了，就需要等待消费者消费
        if(count == chickens.length){
            this.wait();
        }
        //如果没有满，我们就需要丢入产品
        chickens[count] = chicken;
        count++;

        //可以通知消费者消费
        this.notify();

    }


    //消费消费产品
    public synchronized Chicken pop() throws InterruptedException {
        //如果容器中有没有，需要等待生产者生产
        if(count ==0){
            System.out.println("没有鸡了，等待生产");
            this.wait();
        }
        //如果有，消费
        count--;
        Chicken chicken = chickens[count];

        //通知生产者生产
        this.notify();

        return chicken;
    }
}