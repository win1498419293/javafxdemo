package com.example.javafxdemo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import java.io.IOException;
import javafx.scene.web.WebView;


public class HelloApplication extends Application {
    @FXML
    private TextField para;

    @FXML
    private ChoiceBox<?> requmode;

    public void init() throws Exception{

       /** ChoiceBox cb = new ChoiceBox();
        cb.getItems().add("Get");
        cb.getItems().add("Post");
        this.requmode = cb;
        System.out.print("init()");
        **/
        //System.out.print(requmode.getItems());

    }
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 240);
        stage.setTitle("Fofasearch");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}