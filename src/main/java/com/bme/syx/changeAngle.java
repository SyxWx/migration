package com.bme.syx;

public class changeAngle {


    public static void main(String[] args) {

        int startAngle1 = 300;


        int Angle =startAngle1;
        int startAngle = startAngle1;
        startAngle1 = 90 - startAngle1;
        startAngle1 = startAngle1 % 360;
        if (startAngle1 < 0) {startAngle1 += 360;}



        System.out.println(startAngle1);
        startAngle = startAngle - 90 ;
        if(startAngle<0){
            startAngle = -startAngle;
        }
        System.out.println(startAngle);



        Angle = (90 - Angle + 360) % 360;
        System.out.println(Angle);

    }
}
