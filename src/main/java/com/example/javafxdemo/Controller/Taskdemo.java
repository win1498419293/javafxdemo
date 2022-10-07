package com.example.javafxdemo.Controller;

import com.example.javafxdemo.HelloController;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.example.javafxdemo.entity.uiunit;


import static com.example.javafxdemo.Controller.request.myTM.*;


public class Taskdemo extends Task <String>{



    @Override
    protected void updateValue(String value) {
        super.updateValue(value);
    }

    @Override
    protected void updateMessage(String message) {
        super.updateMessage(message);
    }

    @Override
    protected String call() throws Exception {
        request req =new request();
        HelloController hc=new HelloController();
        //获取url
        String urls = hc.urls;
        //获取字典
        String paths = hc.paths;
        System.out.println(paths);
        //获取请求方法
        String requmodes = hc.requmodes;
        //String value = (String) requmode.getValue();
        //System.out.println(value);
        pathpara("src/main/resources/com/example/javafxdemo/dictionary/"+paths);
        int max= queuesize;
        for (int i=1;i<=max;i++){
            String para=req.queue.poll();
            System.out.println(para);
            if(requmodes.equals("Get")){
                getHttp(para,urls);
            }else{
                postHttp(para,urls);
            }
            //Thread.sleep(500);
            updateProgress(i,max);
            System.out.println(i);
        }
        return req.msg.poll();
    }
    public void initialize(){

    }
    public void test() throws Exception {
        Taskdemo tk=new Taskdemo();

    }
}
