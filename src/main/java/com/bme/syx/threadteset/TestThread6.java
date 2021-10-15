package com.bme.syx.threadteset;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

public class TestThread6 implements Callable<Boolean> {


    private String url;
    private String name;

    public TestThread6 (String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloder webDownloder = new WebDownloder();
        webDownloder.downloader(url,name);
        System.out.println("下载的文件名称："+name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestThread6 t1 = new TestThread6("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","1.png");
        TestThread6 t2 = new TestThread6("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","2.png");
        TestThread6 t3 = new TestThread6("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","3.png");

        ExecutorService ser =  Executors.newFixedThreadPool(3);
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);

        ser.shutdownNow();
    }
}
