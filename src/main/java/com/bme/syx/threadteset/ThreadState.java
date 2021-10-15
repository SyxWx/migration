package com.bme.syx.threadteset;

import lombok.SneakyThrows;

import static java.lang.Thread.State.TERMINATED;

public class ThreadState {


    public static void main(String[] args) throws InterruptedException {

        Thread thread = new Thread(()->{
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("////线程结束了");
        });


        // 观察状态
        Thread.State state = thread.getState();
        System.out.println(state);//NEW

        //观察启动后
        thread.start();
        state = thread.getState();
        System.out.println(state);//RUN

        while(thread.getState() != TERMINATED){//只要线程不终止，就一直输出线程状态
            Thread.sleep(500);//减少输出次数
            state = thread.getState();
            System.out.println(state);

        }

    }




}


