package com.example.javafxdemo.Controller;

import com.example.javafxdemo.Check.Poc;
import com.example.javafxdemo.HelloController;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class VulTestViewController extends Task<String> {

    @FXML
    private  TextArea resConsoleField;
    @FXML
    private Button checkVulButField;
    public  HashMap<String, String> vultestinfo=new HashMap<>();
    public  Queue<String> queue =new LinkedList<String>();
    @FXML
    public void initialize() {
        try {
            tool();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void tool(){
        this.checkVulButField.setOnAction(event ->  {
            vultest(HelloController.helloinfo.get("useNum"));
        });
    }
     /**
      * @return
      * @throws Exception
      */
     @Override
     protected void updateValue(String value) {
         super.updateValue(value);
         System.out.println(value);
     }

     @Override
     protected void updateMessage(String message) {
         super.updateMessage(message);
     }
     @Override
     protected String call() throws Exception {
         String vulnums=HelloController.vulnums;
         Poc.checks cs=new Poc.checks();
         vultestinfo.put("vulurls", HelloController.helloinfo.get("vulurls"));
         vultestinfo.put("POC"+vulnums, HelloController.helloinfo.get("POC"+vulnums));
         vultestinfo.put("UA", HelloController.helloinfo.get("UA"));
         vultestinfo.put("Cookie", HelloController.helloinfo.get("Cookie"));
         vultestinfo.put("osType", HelloController.helloinfo.get("osType"));
         vultestinfo.put("Proxy", SettingController.setTing.get("Proxy"));
         Boolean result=cs.s2001(vultestinfo,vulnums);
         if (result){
                 String path=cs.getpath(vultestinfo,vulnums);
                 return  path+vulnums+"??????";

         }else{

             return vulnums+"?????????";
         }
     }

    public void vulcheckall() {
        String vulnums=HelloController.vulnums;
        Poc.checks cs=new Poc.checks();
        HashMap<String, String> vultestinfo=new HashMap<>();
        vultestinfo.put("vulurls", HelloController.helloinfo.get("vulurls"));
        vultestinfo.put("POC"+vulnums, HelloController.helloinfo.get("POC"+vulnums));
        vultestinfo.put("UA", HelloController.helloinfo.get("UA"));
        vultestinfo.put("Cookie", HelloController.helloinfo.get("Cookie"));
        vultestinfo.put("osType", HelloController.helloinfo.get("osType"));
        vultestinfo.put("Proxy", SettingController.setTing.get("Proxy"));
        Boolean result=cs.s2001(vultestinfo,vulnums);
        if (result){
            String path=cs.getpath(vultestinfo,vulnums);
            queue.offer("S2-"+vulnums+"??????");
            System.out.println("S2-"+path+vulnums+"??????");
        }else{
            queue.offer("S2-"+vulnums+"?????????");
            System.out.println("S2-"+vulnums+"?????????");
        }
    }
    public void check046() {
        String vulnums=HelloController.vulnums;
        Poc.checks cs=new Poc.checks();
        HashMap<String, String> vultestinfo=new HashMap<>();
        vultestinfo.put("vulurls", HelloController.helloinfo.get("vulurls"));
        vultestinfo.put("POC"+vulnums, HelloController.helloinfo.get("POC"+vulnums));
        vultestinfo.put("UA", HelloController.helloinfo.get("UA"));
        vultestinfo.put("Cookie", HelloController.helloinfo.get("Cookie"));
        vultestinfo.put("osType", HelloController.helloinfo.get("osType"));
        vultestinfo.put("Proxy", SettingController.setTing.get("Proxy"));
        Boolean result=cs.struts2046(vultestinfo,vulnums);
        if (result){
            String path=cs.getpath(vultestinfo,vulnums);
            queue.offer("S2-"+vulnums+"??????");
            System.out.println("S2-"+path+vulnums+"??????");
        }else{
            queue.offer("S2-"+vulnums+"?????????");
            System.out.println("S2-"+vulnums+"?????????");
        }
    }
     public String vultest(String vulnums){
         Poc.checks cs=new Poc.checks();
         HashMap<String, String> vultestinfo=new HashMap<>();
         vultestinfo.put("vulurls", HelloController.helloinfo.get("vulurls"));
         vultestinfo.put("POC"+vulnums, HelloController.helloinfo.get("POC"+vulnums));
         vultestinfo.put("UA", HelloController.helloinfo.get("UA"));
         vultestinfo.put("Cookie", HelloController.helloinfo.get("Cookie"));
         vultestinfo.put("osType", HelloController.helloinfo.get("osType"));
         vultestinfo.put("Proxy", SettingController.setTing.get("Proxy"));
         vultestinfo.put("vulnum",HelloController.helloinfo.get("useNum"));
         Boolean result=cs.s2001(vultestinfo,vulnums);
         if (result){
             Platform.runLater(new Runnable()
             {
                 @Override
                 public void run()
                 {
                     //???Platform.runLater????????????????????????????????????
                     String path=cs.getpath(vultestinfo,vulnums);
                     resConsoleField.appendText( vultestinfo.get("vulnum")+"??????"+ "\r\n"+HelloController.helloinfo.get("info"));
                     resConsoleField.appendText( path+ "\r\n");
                 }
             });

         }else{
             Platform.runLater(new Runnable()
             {
                 @Override
                 public void run()
                 {
                     //???Platform.runLater????????????????????????????????????
                     resConsoleField.appendText( vultestinfo.get("vulnum")+"?????????"+ "\r\n");

                 }
             });
         }
         return null;
     }
 }