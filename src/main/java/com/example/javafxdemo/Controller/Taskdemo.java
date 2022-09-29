package com.example.javafxdemo.Controller;

import com.example.javafxdemo.HelloController;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import com.example.javafxdemo.entity.uiunit;

import static com.example.javafxdemo.Controller.request.myTM.*;
import static com.example.javafxdemo.Controller.request.queuesize;
import static com.example.javafxdemo.HelloController.paths;
import static com.example.javafxdemo.HelloController.urls;


public class Taskdemo extends Task <String>{


    @FXML
    private Button scanbut;

    @FXML
    private ProgressBar probox;


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
        uiunit ui=new uiunit();
        String requmodes =HelloController.requmodes;
        //获取字典
        String paths =HelloController.paths;
        //获取url
        String urls =HelloController.urls;
        int max= queuesize;
        System.out.println(max);
        for (int i=0;i<=max;i++){
            //Thread.sleep(500);
            String para=req.queue.poll();
            if(requmodes.equals("Get")){
                getHttp(para,urls);
            }else{
                postHttp(para,urls);
            }
            String paras;
            while ((paras = req.msg.poll()) != null) {

                System.out.println(paras);
            }
            updateProgress(i,max);
            System.out.println(req.msg.poll());
        }
        return null;
    }
    public void initialize(){

    }
    public void test() throws Exception {
        Taskdemo tk=new Taskdemo();

    }
}
