package com.example.javafxdemo.Controller;

import com.example.javafxdemo.Check.Payload;
import com.sun.deploy.cache.BaseLocalApplicationProperties;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import com.example.javafxdemo.Check.Exp;
import com.example.javafxdemo.HelloController;


import java.util.HashMap;
import java.util.Map;


public class ExecViewController {


    public static HashMap<String, String> heinfo = HelloController.helloinfo;
    @FXML
    public TextField execTextField;
   @FXML
    public TextArea execReqText;
    public  String result=null;
    public void excecom(ActionEvent actionEvent) {
        String command=execTextField.getText();
        String vulnums= HelloController.helloinfo.get("useNum");
        heinfo.put("vulnum",HelloController.helloinfo.get("useNum"));
        heinfo.put("command",command);
        heinfo.put("osType",HelloController.helloinfo.get("osType"));
        heinfo.put("Proxy", SettingController.setTing.get("Proxy"));
        Exp exp=new Exp();

        if (vulnums.equals("046")&&vulnums.equals("045")) {
           result = exp.s2001(heinfo, vulnums);
        }else{
           result = exp.s2046(heinfo, vulnums);
        }
        Platform.runLater(new Runnable()
        {
            @Override
            public void run()
            {
                //用Platform.runLater来运行需要高频调用的方法
                execReqText.setText(result);
            }
        });
    }
}
