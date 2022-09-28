package com.example.javafxdemo.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

import java.util.LinkedList;
import java.util.Queue;

import static com.example.javafxdemo.Controller.request.myTM.*;
import static com.example.javafxdemo.Controller.request.queuesize;


public class Taskdemo extends Task <String>{


    @FXML
    private Button scanbut;

    @FXML
    private ProgressBar probox;

    @FXML
    private TextArea area;

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
        String requmodes = (String) req.requmode.getValue();
        //获取字典
        String paths = (String) req.combox.getValue();
        //获取url
        String urls = req.url.getText();
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

            updateProgress(i,max);
            area.appendText(para + "\r\n");
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
