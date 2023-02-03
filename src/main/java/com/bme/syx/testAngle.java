package com.bme.syx;

public class testAngle {


    public static void main(String[] args) {



        int Area = 1 ;

        double startAngle = 3;
        double endAngle = 233;
        double RotationAngle = 0;
        double Angle = -1;

        startAngle = 90 - startAngle;
        endAngle = 90 - endAngle;
        startAngle = startAngle % 360;
        if (startAngle < 0) {startAngle += 360;}
        endAngle = endAngle % 360;
        if (endAngle < 0) {endAngle += 360;}
        if (startAngle > endAngle){ endAngle += 360;}
        if (startAngle > Angle) {Angle += 360;}
        RotationAngle = (float)(endAngle - startAngle) / 3;
        switch (Area)
        {
            case 1:
                Angle = startAngle + RotationAngle * 0.5;
                break;
            case 2:
                Angle = startAngle + (RotationAngle * 1.5);
                break;
            case 3:
                Angle = startAngle + (RotationAngle * 2.5);
                break;
        }
        System.out.println(Angle);
        if (Angle < 0) {Angle += 360;}
        System.out.println(Angle);
        Angle = (90 - Angle + 360) % 360;
        System.out.println(Angle);



    }



    /**
     *
     double startAngle = 202;
     double endAngle = 22;

     startAngle = 90-startAngle;
     endAngle = 90-endAngle;

     startAngle = startAngle%360;

     if(startAngle<0){
     startAngle  = startAngle+360;

     }
     if(endAngle<0){
     endAngle  = endAngle+360;

     }

     if(startAngle>-1)


     */

    //返回-1 表示错误
}
