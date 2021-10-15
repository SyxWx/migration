package com.bme.syx.threadteset;

public class ThreadDoamed {


    public static void main(String[] args) {
        God God = new God();
        Yous yous = new Yous();
        Thread godThread = new Thread(God);
        godThread.setDaemon(true);//设置为守护线程，正常的线程不设置的话，默认用户线程
        godThread.start();
        new Thread(yous).start();//普通线程
    }
}

class God implements Runnable{
    @Override
    public void run() {
        while(true){
            System.out.println("上帝守护者你！");
        }
    }
}

class Yous implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i <36500; i++) {
            System.out.println("你健康的活着");
        }
        System.out.println("====godbay,world！===== ");
    }
}