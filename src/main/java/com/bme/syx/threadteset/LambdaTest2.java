package com.bme.syx.threadteset;

public class LambdaTest2 {


    public static void main(String[] args) {

        ILike2 like2 = (a,b)->{
            System.out.println("I like2  Lambda2:"+a);
            System.out.println("I like4  Lambda2:"+b);
        };
        like2.love(2,4);


        ILike1 like1 =() ->System.out.println("I like a");
        like1.love();
    }
}

interface ILike2{
    void love(int a,int b);
}

interface ILike1{
    void love();
}

