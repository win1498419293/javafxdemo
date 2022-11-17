package com.example.javafxdemo;

import com.example.javafxdemo.Controller.FileVulCheckViewTab;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloApplication extends Application {
    @FXML
    private TextField para;

    @FXML
    private ChoiceBox<String> requmode;

    @FXML

    public void init() throws Exception{
        HelloController hc=new HelloController();

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        //Control cl=fxmlLoader.getController();
        stage.setTitle("ak47");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}