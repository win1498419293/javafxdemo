package com.example.javafxdemo.Controller;

import com.example.javafxdemo.HelloController;
import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicInteger;

public class PrintThread extends Thread{

    private  int endports;
    private  int staports;
    private  String ips;
    private int totalThreadNum;//用于端口扫描的总共线程数量，默认为10
    private int threadNo;
    static AtomicInteger  portCount=new AtomicInteger(0);

    public PrintThread(String ips,int threadNo,int staports,int endports){
        this.totalThreadNum = 10;
        this.threadNo = threadNo;
        this.ips=ips;
        this.staports=staports;
        this.endports=endports;
    }


    public void run() {
            try {
                for (int i = staports+threadNo; i < endports+1; i=i+totalThreadNum) {
                    if (isInterrupted()){
                        Thread.interrupted();
                        break;
                    }
                    // 注意，这里不属于swing主线程，所以appendText的内容才会被刷新
                    System.out.println("ip:" + ips + " " + i);
                    try {
                        Socket sk = new Socket();
                        sk.connect(new InetSocketAddress(ips, i), 500);
                        System.out.println("端口" + i + "开放");
                        //updateProgress(i,max);
                        int finalI = i;
                        Platform.runLater(() -> {
                        });
                    } catch (Exception e) {
                        int finalI = i;
                        //e.printStackTrace();
                        System.out.println("端口" + finalI + "未开放");
                    }
                    portCount.incrementAndGet();
                }
                if (portCount.get()==(endports-staports+1)){//判断扫描结束
                    portCount.incrementAndGet();

    }

                sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }


    }


