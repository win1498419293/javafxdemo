package com.example.javafxdemo.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;

import java.util.LinkedList;
import java.util.Queue;

import static com.example.javafxdemo.Controller.request.myTM.start;


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
        start("https://www.jb51.net/","Get","src/main/resources/com/example/javafxdemo/dictionary/spring.txt");
        int max= req.queue.size();
        System.out.println(max);
        for (int i=0;i<=max;i++){
            //Thread.sleep(500);
            updateProgress(i,max);
            System.out.println(i);
        }
        return null;
    }
    public void initialize(){

    }
    public void test() throws Exception {
        Taskdemo tk=new Taskdemo();

    }
}
