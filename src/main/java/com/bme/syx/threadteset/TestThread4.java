package com.bme.syx.threadteset;

public class TestThread4 implements  Runnable{


    private String url;
    private String name;

    public TestThread4 (String url,String name){
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
        TestThread4 t1 = new TestThread4("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","1.png");
        TestThread4 t2 = new TestThread4("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","2.png");
        TestThread4 t3 = new TestThread4("https://www.baidu.com/img/PCtm_d9c8750bed0b3c7d089fa7d55720d6cf.png","3.png");
        new Thread(t1).start();
        new Thread(t2).start();
        new Thread(t3).start();
    }
}
