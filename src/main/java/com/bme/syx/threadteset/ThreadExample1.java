package com.bme.syx.threadteset;

public class ThreadExample1 {

    static Thread thread = null;
    public static boolean runing = true;

    public static void main(String[] args) throws InterruptedException {
        traditional();
        Thread.sleep(2000);
        runing = false;
    }

    public static void traditional(){

        thread = new Thread(){
            @Override
            public void run(){
                while(runing){
                    //aa();
                }
            }
        };
        thread.start();
    }

    public static void  aa(){
        Integer a = 1;
        a.toString();
    }

}
