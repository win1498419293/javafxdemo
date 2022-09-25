package com.example.javafxdemo.Controller;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TabPane;


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
        for (int i=0;i<=100;i++){
            Thread.sleep(100);
            this.updateProgress(i,100);
            System.out.println(i);
        }
        return null;
    }
    public void initialize(){

    }
    public void test() throws Exception {
        //Taskdemo tk=new Taskdemo();
        //Thread td=new Thread(tk);
       // probox.progressProperty().bind(tk.progressProperty());
        //new Thread(tk).start();

    }
}
