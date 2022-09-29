package com.example.javafxdemo;

import com.example.javafxdemo.Controller.Taskdemo;
import com.example.javafxdemo.Controller.request;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.example.javafxdemo.Controller.base64endode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import com.example.javafxdemo.entity.uiunit;

import static com.example.javafxdemo.Controller.request.myTM.pathpara;
import static com.example.javafxdemo.Controller.request.myTM.start;

public class HelloController {



    @FXML
    public TextArea area;

    @FXML
    private Button scanbut;

    @FXML
    private Button search;

    @FXML
    private ChoiceBox<?> requmode;

    @FXML
    private WebView webview;

    @FXML
    private TextField threadbox;

    @FXML
    private TextField para;

    @FXML
    private Button threadbut;

    @FXML
    public ComboBox<String> combox;

    @FXML
    private TextArea text;

    @FXML
    private TextField url;

    @FXML
    private Label threadlabel;

    @FXML
    private Label urllabel;

    public String path;

    @FXML
    private ProgressBar probox;

    @FXML
    private ProgressBar proboxone;

    public static String paths ;

    public static String urls ;
    public static String requmodes ;





    /**
     * 在加载布景前加载的方法，将字典填充到combox
     * */
    public void initialize () {

        String path = "src/main/resources/com/example/javafxdemo/dictionary/";
        File f = new File(path);
        if (!f.exists()) {
            System.out.println(path + " not exists");
            return;
        }
        File result[] = f.listFiles();
        for (int i = 0; i < result.length; i++) {
            File fs = result[i];
            combox.getItems().add(fs.getName());
        }
        combox.getSelectionModel().select(0);
    }


    /**
     * 设置字典方法
     */
    @FXML
    void setdic(ActionEvent event) {

        //获得字典的值
       String paths = combox.getValue();
       //判断没有选择字典时设置默认字典
        if (paths==""){
            path = "src/main/resources/com/example/javafxdemo/dictionary/spring.txt";
        }else{
            path = "src/main/resources/com/example/javafxdemo/dictionary/"+paths;
        }
        System.out.println(combox.getValue());
    }


    /**
     *单线程扫描方法
     */

    @FXML
    void stratscan(ActionEvent event) throws Exception {
        //获取url
        String urls = url.getText();
        request re = new request();
        //获取请求方法类型 post or get
        String requmodes = (String) requmode.getValue();
        System.out.println(requmodes);
        System.out.println(urls);
        //获取字典框值
        String paths = combox.getValue();
        //判断字典值跟url是否为空
        if (paths==""||urls.equals("")){
            //设置label文本样式
            urllabel.setTextFill(Color.web("#0ea30e"));
            urllabel.setFont(Font.font("Cambria", 15));
            urllabel.setText("请输入url");
            //设置默认字典
        }else{
            path = "src/main/resources/com/example/javafxdemo/dictionary/spring.txt";
            String regex = "((http|https)://)([\\w-]+\\.)+[\\w$]+";
            String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w$]+(\\/[\\w-?=&./]*)?$";//[.?*]表示匹配的就是本身
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urls);
            //正则匹配url是否为正确url
            if (matcher.find()){
                System.out.println("满足格式！");
                start(urls, requmodes, path);
                String para;
                while ((para = re.msg.poll()) != null) {
                    area.appendText(para + "\r\n");
                    System.out.println(para);
                }
            }else {
                System.out.println("不满足格式！");
                urllabel.setText("请输入正确的url");
            }
            path = "src/main/resources/com/example/javafxdemo/dictionary/"+paths;
        }

    }


    /**
     *
     *
     */

    @FXML
    void searchfofa(ActionEvent event) {
        String paratext = para.getText();
        String cookies = "befor_router=; Hm_lvt_19b7bde5627f2f57f67dfb76eedcf989=1661350264; fofa_token=eyJhbGciOiJIUzUxMiIsImtpZCI6Ik5XWTVZakF4TVRkalltSTJNRFZsWXpRM05EWXdaakF3TURVMlkyWTNZemd3TUdRd1pUTmpZUT09IiwidHlwIjoiSldUIn0.eyJpZCI6ODg3NjQsIm1pZCI6MTAwMDU0NDc5LCJ1c2VybmFtZSI6InllbG91IiwiZXhwIjoxNjYxNjA5Njk0fQ.hxksGY6yRLm35xSijjGQvh6U-utr7lJUFf8CqQiRh6B3iJ3_ItvYO90tr8e7D-DZEqFFmcyJ992cfkeVLOWg5Q; user=%7B%22id%22%3A88764%2C%22mid%22%3A100054479%2C%22is_admin%22%3Afalse%2C%22username%22%3A%22yelou%22%2C%22nickname%22%3A%22%22%2C%22email%22%3A%22z3151431902%40gmail.com%22%2C%22avatar_medium%22%3A%22https%3A%2F%2Fnosec.org%2Fmissing.jpg%22%2C%22avatar_thumb%22%3A%22https%3A%2F%2Fnosec.org%2Fmissing.jpg%22%2C%22key%22%3A%22b22b91938bbd58fb468878ba29b6bf2e%22%2C%22rank_name%22%3A%22%E6%B3%A8%E5%86%8C%E7%94%A8%E6%88%B7%22%2C%22rank_level%22%3A0%2C%22company_name%22%3A%22%22%2C%22coins%22%3A0%2C%22can_pay_coins%22%3A0%2C%22credits%22%3A1%2C%22expiration%22%3A%22-%22%2C%22login_at%22%3A1661350494%7D; baseShowChange=false; Hm_lpvt_19b7bde5627f2f57f67dfb76eedcf989=1661350569";
        base64endode base64 = new base64endode();
        String qbase64 = base64.base64(paratext);
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
            url = new URL("https://fofa.info/result?qbase64=" + qbase64);
            WebView browser = new WebView();
            WebEngine webEngine = browser.getEngine();
            webEngine.load("https://fofa.info/result?qbase64=" + qbase64);
            AnchorPane anc = new AnchorPane(browser);
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
            while ((str = br.readLine()) != null) {
                a.append(str);
                text.setText(a.toString());
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                //关闭流
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     *
     *设置多线程方法
     */
    @FXML
    public void setthread(ActionEvent actionEvent) throws Exception, InvocationTargetException {

        //获取字典
         paths = combox.getValue();
        //获取url
        urls = url.getText();
        //获取请求方法
         requmodes = (String) requmode.getValue();
        //获得线程数
        int threadnum = 5;
        request re = new request();
        if (urls.equals("")){
            urllabel.setTextFill(Color.web("#0ea30e"));
            urllabel.setFont(Font.font("Cambria", 15));
            urllabel.setText("请输入url");
            path = "src/main/resources/com/example/javafxdemo/dictionary/spring.txt";
        }else{

            //判断输入的是否是数字及是否为空
            if (!threadbox.getText().equals("")) {
                try{
                    threadnum = Integer.parseInt(threadbox.getText());
                    if (threadnum < '0' || threadnum > '9') ;
                }catch (Exception e){
                    threadlabel.setText("请输入数字");
                }
            }else {
                threadnum = 5;
            }
            path = "src/main/resources/com/example/javafxdemo/dictionary/"+paths;
            String regex = "((http|https)://)([\\w-]+\\.)+[\\w$]+";
            String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w$]+(\\/[\\w-?=&./]*)?$";//[.?*]表示匹配的就是本身
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urls);
            if (matcher.find()){
                System.out.println("满足格式！");
                pathpara(path);
                //start(urls, requmodes, path);
                //re.StartThread(urls, threadnum, requmodes, path);
                Task task = new Task<Void>() {
                    @Override
                    public Void call() {
                        String para;
                        while ((para = re.msg.poll()) != null) {
                            area.appendText(para + "\r\n");
                            System.out.println(para);
                        }
                        return null;
                    }
                };
                Taskdemo tk=new Taskdemo();
                ExecutorService executorService = Executors.newFixedThreadPool(5);
                proboxone.progressProperty().bind(tk.progressProperty());
                executorService.submit(tk);
            }else {
                System.out.println("不满足格式！");
                urllabel.setText("请输入正确的url");
            }
        }
        }

    public void startpro(ActionEvent actionEvent) throws Exception {
        Taskdemo tk=new Taskdemo();
        Thread td=new Thread(tk);
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        /**Task task = new Task<Void>() {
            @Override
            public Void call() {
                for (int i = 1; i <= 100; i++) {
                    if (isCancelled()) {
                        break;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException interrupted) {
                        if (isCancelled()) {
                            break;
                        }
                    }
                    updateProgress(i, 100);
                }
                return null;
            }
        };
         **/
        probox.progressProperty().bind(tk.progressProperty());
        executorService.submit(tk);
        //probox.progressProperty().bind(tk.progressProperty());
        //new Thread(tk).start();
    }
    public  String showmsg(String msg){
        return msg;
    }
}
