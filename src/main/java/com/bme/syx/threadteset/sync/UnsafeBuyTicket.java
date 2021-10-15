package com.bme.syx.threadteset.sync;


//不安全的取票
//3个人买票，买到相同的票
public class UnsafeBuyTicket {
    public static void main(String[] args) {
        BuyTicket buyTicket = new BuyTicket();

        Thread thread1 = new Thread(buyTicket,"苦逼的我");
        Thread thread2 = new Thread(buyTicket,"牛逼的你们");
        Thread thread3 = new Thread(buyTicket,"客户的黄牛党");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}


class BuyTicket implements  Runnable{
    //票
    private int ticketNums = 10;

    private boolean flag = true;

    @Override
    public void run() {
        //买票
        while(flag){
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //买票 方法
    private void buy() throws InterruptedException {
        //判断票是否还有余票
        if(ticketNums <= 0){
            flag = false;
            return;
        }
        // 模拟延时
        Thread.sleep(300);
        //买票
        System.out.println(Thread.currentThread().getName()+":买到票了,"+ticketNums+";余票："+ticketNums-- +"张！！");

    }
}