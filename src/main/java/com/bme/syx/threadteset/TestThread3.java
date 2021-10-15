package com.bme.syx.threadteset;

public class TestThread3 implements Runnable{

    @Override
    public void run(){
        for (int i = 0; i < 100 ; i++) {
            System.out.println("我是线程thread----"+i);
        }
    }
    public static  void  main(String[] args){
        TestThread3 testThread3 = new TestThread3();
        new Thread(testThread3).start();
        for (int i = 0; i <100; i++) {
            System.out.println("我是主线程main----"+i);
        }
    }
}
