package com.bme.syx.threadteset.sync;

import java.util.ArrayList;

public class ThreadArrayList {

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList();

        for (int i = 0; i < 60000; i++) {
            new Thread(()->{
                arrayList.add(Thread.currentThread().getName());
            }).start();
        }
        System.out.println(arrayList.size());
    }
}
