package com.bme.syx.threadteset.sync;


//不安全的取钱
//2个人对同一个账户取钱，完成后取到的钱大于原本账户中的钱
public class UnsafeBank {


    public static void main(String[] args) {
        Accout accout = new Accout(1000,"结婚基金");

        Drawing   you  = new Drawing(accout,50,"小王");

        Drawing girlFriend = new Drawing(accout,100,"grilFriend");

        you.start();
        girlFriend.start();

    }
}


//账户
class Accout{
    int money;
    String  name;

    public Accout(int money,String name){
        this.money = money;
        this.name = name;
    }

}


//银行
class Drawing extends Thread{
    //账户
    Accout accout;

    //取了多少钱
    int drawingMoney;

    //余额
    int nowMoney;

    public Drawing(Accout accout,int drawingMoney , String  name){
        super(name);
        this.accout = accout;
        this.drawingMoney = drawingMoney;

    }

    //取钱

    @Override
    public    void  run(){

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        while(true) {
            synchronized (accout) {
                //判断余额是否充足
                if (accout.money - drawingMoney < 0) {
                    System.out.println(Thread.currentThread().getName() + ":余额不足，取钱失败");
                    return;
                }
                // 卡内余额扣款
                accout.money = accout.money - drawingMoney;

                //手中的钱
                nowMoney = nowMoney + drawingMoney;

                System.out.println("--------------------------" + this.getName() + "你取钱：" + drawingMoney + "成功；" + "-------------------------------");
                System.out.println(accout.name + "账户余额剩余:" + accout.money);
                // this.getName()  =  Thread.currentThread().getName()
                System.out.println(this.getName() + "手中钱：" + nowMoney);
                System.out.println("---------------------------------------------------------");
            }

        }

    }


}