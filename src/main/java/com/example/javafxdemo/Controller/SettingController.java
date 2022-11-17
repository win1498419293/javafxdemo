package com.example.javafxdemo.Controller;

import com.sun.deploy.cache.BaseLocalApplicationProperties;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;


import java.util.HashMap;

public class SettingController {
    public static HashMap <String,String>setTing=new HashMap<>();
    @FXML
    private TextArea uaTextArea;
    @FXML
    private  TextArea cookieTextArea;

    @FXML
    private CheckBox checkproxy;
    @FXML
    private javafx.scene.control.TextField proxyField;
    @FXML
    private void initialize() {
        try {
            initSetting();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initSetting() {
        setTing.put("Proxy", "");
        this.checkproxy.selectedProperty().addListener((observable, oldValue, newValue) -> {

            if (newValue) {
                setTing.put("Proxy", proxyField.getText());
            }else {
                setTing.put("Proxy", "");
            }
        });

        setTing.put("UA", uaTextArea.getText());
        setTing.put("Cookie", cookieTextArea.getText());
    }

}
