package com.bme.syx.threadteset.sync;

import java.util.ArrayList;

public class ThreadArrayList {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                synchronized (arrayList){
                    arrayList.add(Thread.currentThread().getName());
                }
            }).start();
        }
        try {
            // 沉睡 主线程main  等待60000个现场都执行完成
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("synchronized:::"+arrayList.size());


        ArrayList list = new ArrayList();

        for (int i = 0; i < 10000; i++) {
            new Thread(()->{
                 list.add(Thread.currentThread().getName());
            }).start();
        }
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("not synchronized:::"+list.size());
    }
}
