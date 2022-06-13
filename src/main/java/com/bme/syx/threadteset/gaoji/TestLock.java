package com.bme.syx.threadteset.gaoji;


import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.locks.ReentrantLock;

public class TestLock {

    public static void main(String[] args) {


        TestLock3 t3 = new TestLock3();
        new Thread(t3).start();
        new Thread(t3).start();
        new Thread(t3).start();

    }
}



//不安全的减票
class TestLock2 implements Runnable{
    int ticketNums = 10;
    @Override
    public void run() {
        while(true){
            if(ticketNums>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(ticketNums--);
            }else{
                break;
            }
        }
    }
}

//不安全的减票
class TestLock3 implements Runnable{
    int ticketNums = 10;

    // 定义lock锁
    private final ReentrantLock lock = new ReentrantLock();
    @Override
    public void run() {
        while(true){
            try {
                lock.lock();
                if(ticketNums>0){
                    System.out.println(ticketNums--);
                }else{
                    break;
                }
            }finally {
                lock.unlock();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}