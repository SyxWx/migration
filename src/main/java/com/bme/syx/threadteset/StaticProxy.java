package com.bme.syx.threadteset;

public class StaticProxy {


    public static void main(String[] args) {
//
//        WeddingCompany weddingCompany = new WeddingCompany(you);
//        weddingCompany.happyMarry();

        You you = new You();
        new Thread(()->System.out.println("结婚了线程")).start();
        new WeddingCompany(you).happyMarry();
    }


}
interface Marry{

    void happyMarry();
}
class  You implements Marry{
    @Override
    public void happyMarry() {
        System.out.println("你要结婚了，开心不");
    }
}
class WeddingCompany implements  Marry{

    private  Marry target;

    public  WeddingCompany(Marry target){
        this.target = target;
    }

    @Override
    public void happyMarry() {

        before();
        this.target.happyMarry();
        after();
    }

    private void before(){
        System.out.println("婚前");
    }

    private  void after(){
        System.out.println("婚后");
    }
}