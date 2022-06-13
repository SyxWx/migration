package com.bme.syx.threadteset.sync;


//不安全的取票
//3个人买票，买到相同的票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();
        BuyTicket buyTicket2 = new BuyTicket();
        BuyTicket buyTicket3 = new BuyTicket();


        new Thread(buyTicket,"线程1").start();
        new Thread(buyTicket,"线程2").start();
        new Thread(buyTicket,"线程3").start();

    }

}


class BuyTicket implements  Runnable{
    //票
    private int ticketNums = 20;
    private boolean flag = true;
    @Override
    public void run() {
        //买票
        while(flag){

            try {
                // 模拟延时

                buy();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //买票 方法
    private synchronized void buy() throws InterruptedException {
        //判断票是否还有余票
        if(ticketNums <= 0){
            flag = false;
            return;
        }
        //买票
        System.out.println(Thread.currentThread().getName()+":买到票了,"+ticketNums-- +";余票："+ticketNums +"张！！");

    }
}