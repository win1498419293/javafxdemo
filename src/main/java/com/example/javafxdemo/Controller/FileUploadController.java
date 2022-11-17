package com.example.javafxdemo.Controller;

import com.example.javafxdemo.Check.Exp;
import com.example.javafxdemo.Check.Payload;
import com.example.javafxdemo.Check.Poc;
import com.example.javafxdemo.HelloController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.HashMap;
import java.util.Map;

public class FileUploadController {
    @FXML
    private Button rootUploadBut;
    @FXML
    private Button customUploadBut;

    @FXML
    private TextField rootPathFileName;

    @FXML
    private TextField realPathFileName;

    @FXML
    private TextArea showFileContent;
    @FXML
    private Label resUpload;

    public static HashMap<String, String> heinfo = HelloController.helloinfo;

    @FXML
    private void initialize(){
        uploadfile();
    }
    private  void uploadfile(){

        Poc.checks cs=new Poc.checks();
        this.rootUploadBut.setOnAction(event -> {
            String rootfe=rootPathFileName.getText();
            String content=showFileContent.getText();
            String vulnums= HelloController.helloinfo.get("useNum");
            heinfo.put("vulnum",HelloController.helloinfo.get("useNum"));
            heinfo.put("osType",HelloController.helloinfo.get("osType"));
            heinfo.put("Proxy", SettingController.setTing.get("Proxy"));
            heinfo.put("osType", HelloController.helloinfo.get("osType"));
            heinfo.put("rootfe", rootfe);
            heinfo.put("content", content);
            if (!vulnums.equals("045")&&!vulnums.equals("045")) {
                String result = cs.uploadfile(heinfo, vulnums, "USERUPLOAD");
                resUpload.setText(result);
            }else {
                String result = cs.s046upfile(heinfo, vulnums, "USERUPLOAD");
                resUpload.setText(result);
            }

        });
        this.customUploadBut.setOnAction(event -> {
            String realfe=realPathFileName.getText();
            String rootfe=rootPathFileName.getText();
            String content=showFileContent.getText();
            String vulnums= HelloController.helloinfo.get("useNum");
            heinfo.put("vulnum",HelloController.helloinfo.get("useNum"));
            heinfo.put("osType",HelloController.helloinfo.get("osType"));
            heinfo.put("Proxy", SettingController.setTing.get("Proxy"));
            heinfo.put("osType", HelloController.helloinfo.get("osType"));
            heinfo.put("rootfe", realfe+rootfe);
            heinfo.put("content", content);
            if (!vulnums.equals("045")&&!vulnums.equals("045")) {
                String result = cs.uploadfile(heinfo, vulnums, "USERUPLOAD");
                resUpload.setText(result);
            }else {
                String result = cs.s046upfile(heinfo, vulnums, "USERUPLOAD");
                resUpload.setText(result);
            }
        });

    }

}
