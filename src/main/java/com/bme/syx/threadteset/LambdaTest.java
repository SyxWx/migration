package com.bme.syx.threadteset;

public class LambdaTest {


    static class Like implements ILike{
        @Override
        public void love() {
            System.out.println("I like  Lambda");
        }
    }

    public static void main(String[] args) {


        class Like implements ILike{
            @Override
            public void love() {
                System.out.println("I like  Lambda");
            }
        }

        ILike like = new ILike() {
            @Override
            public void love() {
                System.out.println("I like  Lambda2");
            }
        };
        like.love();


        ILike like2 = () -> {
                System.out.println("I like  Lambda3");
        };

        like2.love();



    }


}





interface ILike{
    void love();
}

