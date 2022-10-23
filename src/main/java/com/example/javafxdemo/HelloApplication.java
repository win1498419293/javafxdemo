package com.example.javafxdemo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import java.io.IOException;
import javafx.scene.web.WebView;
import javafx.stage.WindowEvent;


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
        Parent root= fxmlLoader.load();
        Scene scene = new Scene(root );
        //Control cl=fxmlLoader.getController();
        stage.setTitle("Fofasearch");
        stage.setScene(scene);
        stage.show();
        stage.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // 更新GUI组件
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        Scene scene = stage.getScene();
//                        // 自适应按钮 对应 fx:id
//                        Button threadbut = (Button) scene.lookup("#threadbut");
//                        Button scanbut = (Button) scene.lookup("#scanbut");
//                        TextField  url=(TextField) scene.lookup("#url");
//                        TextField  threadbox=(TextField) scene.lookup("#threadbox");
//                        Label  threadlabel=(Label) scene.lookup("#threadlabel");
//                        Label  urllabel=(Label) scene.lookup("#urllabel");
//                        Label  proboxlab=(Label) scene.lookup("#proboxlab");
//                        double width = stage.getWidth();
//                        url.setPrefWidth(width * 0.5);
//                        threadbox.setPrefWidth(width * 0.1);
//                        threadbut.setPrefWidth(width * 0.1);
//                        scanbut.setPrefWidth(width * 0.1);
//                        threadlabel.setPrefWidth(width * 0.1);
//                        urllabel.setPrefWidth(width * 0.1);
//                        proboxlab.setPrefWidth(width * 0.1);
//
//                    }
//                });
            }
        });

        stage.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                // 具体操作
                /*Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Scene scene = stage.getScene();
                        // 自适应按钮 对应 fx:id
                        Button button = (Button) scene.lookup("#threadbut");
                        TextField  url=(TextField) scene.lookup("#url");
                        TextField  threadbox=(TextField) scene.lookup("#threadbox");
                        Button scanbut = (Button) scene.lookup("#scanbut");
                        Label  threadlabel=(Label) scene.lookup("#threadlabel");
                        Label  urllabel=(Label) scene.lookup("#urllabel");
                        Label  proboxlab=(Label) scene.lookup("#proboxlab");
                        double height = stage.getHeight();
                        url.setPrefHeight(height * 0.1);
                        threadbox.setPrefHeight(height * 0.1);
                        button.setPrefHeight(height * 0.1);
                        scanbut.setPrefHeight(height * 0.1);
                        threadlabel.setPrefHeight(height * 0.1);
                        urllabel.setPrefHeight(height * 0.1);
                        proboxlab.setPrefHeight(height * 0.1);

                    }
                });*/
            }
        });


        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        launch();
    }
}