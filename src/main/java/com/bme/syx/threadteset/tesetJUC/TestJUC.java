package com.bme.syx.threadteset.tesetJUC;


import java.util.concurrent.CopyOnWriteArrayList;

// 测试
public class TestJUC {

    public static void main(String[] args) {


        CopyOnWriteArrayList<String> copylist =  new CopyOnWriteArrayList();

        for (int i = 0; i < 10000; i++) {

            new Thread(()->{
                copylist.add(Thread.currentThread().getName());
            }).start();

        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        System.out.println(copylist.size());
    }


}
