package com.bme.syx.threadteset;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread2 extends Thread{


    private String url;
    private String name;

    public TestThread2 (String url,String name){
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloder webDownloder = new WebDownloder();
        webDownloder.downloader(url,name);
        System.out.println("下载的文件名称："+name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","1.png");
        TestThread2 t2 = new TestThread2("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","2.png");
        TestThread2 t3 = new TestThread2("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","3.png");
        t1.start();
        t2.start();
        t3.start();

    }
}


