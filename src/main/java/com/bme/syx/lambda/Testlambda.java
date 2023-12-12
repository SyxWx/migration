package com.bme.syx.lambda;

public class Testlambda {


    public static void main(String[] args) {
        MyMarry myMarry =  new MyMarry();
        myMarry.happymarry();

    }


}



interface Marry{
    void happymarry();
}

class MyMarry implements Marry{

    @Override
    public void happymarry() {
        System.out.println("I marry");
    }
}