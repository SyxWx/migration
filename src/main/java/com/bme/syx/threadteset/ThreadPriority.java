package com.bme.syx.threadteset;

import java.sql.SQLOutput;

public class ThreadPriority  {


    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }

    MyPriority myPriroity = new MyPriority();



}

class MyPriority implements Runnable{

    @Override
    public void run(){
        System.out.println(Thread.currentThread().getName()+"-->"+Thread.currentThread().getPriority());
    }
}
