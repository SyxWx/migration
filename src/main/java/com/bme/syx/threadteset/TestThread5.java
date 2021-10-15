package com.bme.syx.threadteset;

public class TestThread5 implements  Runnable {


    private int ticketNums = 10;
    @Override
    public void run() {
        while(true){
            if(ticketNums<=0){
                break;
            }
            // 模拟延时
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"-->"+ticketNums--+"票");
        }
    }


    public static void main(String[] args) {
        TestThread5 testThread5 = new TestThread5();

        for (int i = 0; i <20 ; i++) {
            new Thread(testThread5,"线程："+i).start();
        }

    }
}
