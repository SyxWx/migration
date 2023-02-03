package com.bme.syx.threadteset;

public class ThreadExample {

    static Thread thread = null;
    public static boolean runing = true;

    public static void main(String[] args) throws InterruptedException {
        traditional();
        Thread.sleep(1000);
        thread.interrupt();
    }

    public static void traditional(){

        thread = new Thread(){
          @Override
          public void run(){
              while(!this.isInterrupted()){

                  try {
                      Thread.sleep(10000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  System.out.println("线程正在跑");
              }
          }
        };
        thread.start();
    }
}
