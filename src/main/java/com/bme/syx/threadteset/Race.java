package com.bme.syx.threadteset;

//模拟龟兔赛跑
public class Race implements  Runnable {


    private static String winner;
    @Override
    public void run() {
        for (int i = 0; i < 101; i++) {

            if(Thread.currentThread().getName().equals("兔子") && i%10 == 0){
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if(gameover(i)){
                break;
            }

            System.out.println(Thread.currentThread().getName()+"-->跑了："+i+"步");
        }
    }

    private boolean gameover(int step){
        if(winner!=null){
            return true;
        }
        if(step >= 100){
            winner = Thread.currentThread().getName();
            System.out.println(winner+",赢了");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"乌龟").start();
        new Thread(race,"兔子").start();
    }
}
