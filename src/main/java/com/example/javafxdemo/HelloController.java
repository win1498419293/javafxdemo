package com.example.javafxdemo;

import com.example.javafxdemo.Controller.request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.example.javafxdemo.Controller.base64endode;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import static com.example.javafxdemo.Controller.request.StartThread;
import static com.example.javafxdemo.Controller.request.myTM.start;

public class HelloController {

    @FXML
    private Button search;
    @FXML
    private TextArea text;

    @FXML
    private TextField para;
    @FXML
    private Button button;

    @FXML
    private ComboBox combox;

    @FXML
    private TextField threadbox;

    @FXML
    private TextField url;
    @FXML
    void stratscan(ActionEvent event) throws Exception {
        String urls = url.getText();
        int intIndex = urls.indexOf("/");
        int length = urls.length();
        System.out.println("length"+length);
        System.out.println("intIndex"+intIndex);
        if(intIndex == length+1){
            System.out.println(urls);
        }else{
            urls=urls+"/";
        }
        start(urls);
    }
    @FXML
    void searchfofa(ActionEvent event) {
        String paratext = para.getText();
        String cookies = "befor_router=; Hm_lvt_19b7bde5627f2f57f67dfb76eedcf989=1661350264; fofa_token=eyJhbGciOiJIUzUxMiIsImtpZCI6Ik5XWTVZakF4TVRkalltSTJNRFZsWXpRM05EWXdaakF3TURVMlkyWTNZemd3TUdRd1pUTmpZUT09IiwidHlwIjoiSldUIn0.eyJpZCI6ODg3NjQsIm1pZCI6MTAwMDU0NDc5LCJ1c2VybmFtZSI6InllbG91IiwiZXhwIjoxNjYxNjA5Njk0fQ.hxksGY6yRLm35xSijjGQvh6U-utr7lJUFf8CqQiRh6B3iJ3_ItvYO90tr8e7D-DZEqFFmcyJ992cfkeVLOWg5Q; user=%7B%22id%22%3A88764%2C%22mid%22%3A100054479%2C%22is_admin%22%3Afalse%2C%22username%22%3A%22yelou%22%2C%22nickname%22%3A%22%22%2C%22email%22%3A%22z3151431902%40gmail.com%22%2C%22avatar_medium%22%3A%22https%3A%2F%2Fnosec.org%2Fmissing.jpg%22%2C%22avatar_thumb%22%3A%22https%3A%2F%2Fnosec.org%2Fmissing.jpg%22%2C%22key%22%3A%22b22b91938bbd58fb468878ba29b6bf2e%22%2C%22rank_name%22%3A%22%E6%B3%A8%E5%86%8C%E7%94%A8%E6%88%B7%22%2C%22rank_level%22%3A0%2C%22company_name%22%3A%22%22%2C%22coins%22%3A0%2C%22can_pay_coins%22%3A0%2C%22credits%22%3A1%2C%22expiration%22%3A%22-%22%2C%22login_at%22%3A1661350494%7D; baseShowChange=false; Hm_lpvt_19b7bde5627f2f57f67dfb76eedcf989=1661350569";
        base64endode base64 = new base64endode();
        String qbase64=base64.base64(paratext);
        URL url = null;
        //创建连接
        URLConnection conn = null;
        //字节输入流
        InputStream is = null;
        //字节转换为字符流
        InputStreamReader isr = null;
        //缓冲流
        BufferedReader br = null;

        //爬取内容存储的容器
        String str = null;
        //明确你要爬取内容的地址
        try {
            //java中的异常处理机制 Java中的异常是有责任制的 不是所有的异常都往外抛
            url = new URL("https://fofa.info/result?qbase64="+qbase64);
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load("https://fofa.info/result?qbase64="+qbase64);
            AnchorPane anc =new AnchorPane(browser);
            System.out.print(url);
            //创建连接
            conn = url.openConnection();
            conn.addRequestProperty("Cookie", cookies);
            //使用IO流
            //字节流 每次只能读取一个字节的内容，可以保证内容的完整性，但是效率很低
            is = conn.getInputStream();
            //将字节流升级为字符流，每次可以读取一个字符的内容
            isr = new InputStreamReader(is);
            //将字符流转为字符缓冲流，使用缓冲流的时候要特别注意，最后一次用完了要关闭才能将里面的内容全部读到
            //否则你就要使用自动刷新
            br = new BufferedReader(isr);

            StringBuilder a = new StringBuilder();
            //读取一行内容
            while((str = br.readLine()) != null){
                a.append(str);
                text.setText(a.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null){
                //关闭流
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setthread(ActionEvent actionEvent) {
        int threadnum = Integer.parseInt(threadbox.getText());
        StartThread(threadnum);
    }
}
