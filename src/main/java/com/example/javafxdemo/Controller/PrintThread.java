package com.example.javafxdemo.Controller;

import com.example.javafxdemo.HelloController;

public class PrintThread extends Thread{
    private HelloController hc;
    private String msg;
    public PrintThread(){

    }

    public PrintThread(HelloController hc, String test1) {
        this.hc=hc;
        this.msg=msg;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            // 注意，这里不属于swing主线程，所以appendText的内容才会被刷新

            try {
                sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
    public void gotoMain() {
        try {
            HelloController hc = new HelloController();
            // 新开三个线程，每个线程往TextArea不断输出指定字符串
            new PrintThread(hc, "test1").start();
            new PrintThread(hc, "test2").start();
            new PrintThread(hc, "test3").start();
        } catch (Exception e) {
        }
    }

}
