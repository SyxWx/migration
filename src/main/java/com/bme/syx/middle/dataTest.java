package com.bme.syx.middle;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class dataTest {

    public static void main(String[] args) {
        Date date = new Date(System.currentTimeMillis());
//        int per = 30;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//        long nowdata = System.currentTimeMillis();
//        long  maxdate = Long.valueOf(sdf.format(date))+(30*1000);
//        long  mindate = Long.valueOf(sdf.format(date))-(30*1000);
//        System.out.println(maxdate+"-----------"+maxdate);
//        System.out.println(mindate+"-----------"+mindate);
//
//        Date maxdates = new Date(maxdate);
//        Date mindates = new Date(mindate);
//        System.out.println(maxdates+"-----------"+maxdates);
//        System.out.println(date+"-----------"+date);
//        System.out.println(mindates+"-----------"+mindates);


//   192.168.1.233  jhgc12345
        //  String ip = "192.168.1.186";
        String  imgurl = "./Image/2022-01-10/plate_Time_20220111_193344_1-1-22751.jpg";
        imgurl =   imgurl.replaceAll("./Image","D:/bme/uploadData/dahua/Image");


        System.out.println(imgurl);


    }
}
