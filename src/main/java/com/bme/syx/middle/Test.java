package com.bme.syx.middle;

public class Test
{



    public static void main(String[] args) {



        String channelId = "10";
        System.out.println(channelId);

        if(channelId.length() < 10){
            channelId = (int)Float.parseFloat(channelId)+"";
        }
        System.out.println(channelId);

    }
}
