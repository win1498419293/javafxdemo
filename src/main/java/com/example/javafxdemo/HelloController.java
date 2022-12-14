package com.example.javafxdemo;

import com.example.javafxdemo.Check.Exp;
import com.example.javafxdemo.Check.Payload;
import com.example.javafxdemo.Check.Poc;
import com.example.javafxdemo.Controller.*;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import com.example.javafxdemo.entity.uiunit;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import static com.example.javafxdemo.Controller.request.myTM.*;

public class HelloController {

    @FXML
    private TextArea area;

    @FXML
    private WebView webview;

    @FXML
    private TextField threadbox;



    @FXML
    private ProgressBar probox;


    @FXML
    private ChoiceBox<String> requmode;


    @FXML
    private Label threadlabel;

    @FXML
    private Label proboxlab;

    @FXML
    private TextField url;

    @FXML
    private ProgressBar proboxone;

    @FXML
    private Button search;

    @FXML
    private TextField para;

    @FXML
    private ComboBox<String> combox;

    @FXML
    private TextField targetField;
    @FXML
    public ChoiceBox<String> setEncodeField;
    @FXML
    public ChoiceBox<String> s2_allField;
    @FXML
    public ChoiceBox<String> methodField;

    @FXML
    private TextArea showarea;

    @FXML
    private TextArea text;

    @FXML
    private Label urllabel;

    @FXML
    private TextField endport;

    @FXML
    private TextField threadnum;

    @FXML
    private Button scanbut;

    @FXML
    private Button startbut;


    @FXML
    private ComboBox<String> vulcombox;


    @FXML
    private TextField staport;

    @FXML
    private TextField ip;

    @FXML
    private TextField vulurl;

    @FXML
    private Button urlscanbut;

    @FXML
    private  TextArea resConsoleField;



    private String path;

    public static String paths;

    public static String urls ;

    public static String  requmodes;
    public  Thread tk;

    public  Thread td;
    static AtomicInteger portCount=new AtomicInteger(0);
    public static HashMap<String,String> helloinfo=new HashMap<>();
    public static String vulnums="";

    /**
     * ??????????????????????????????????????????????????????combox
     * */
    public void initialize () {
        try {
            initMainInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void initMainInfo() {
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
        this.vulurl.textProperty().addListener((observable, oldValue, newValue) -> helloinfo.put("vulurls", newValue));
        combox.getSelectionModel().select(0);
        vulcombox.getItems().add("S2-001");
        vulcombox.getSelectionModel().select(0);
        setEncodeField.setValue("UTF-8");
        setEncodeField.getItems().addAll("UTF-8", "GBK2312", "GBK");
        methodField.setValue("POST");
        methodField.getItems().addAll("POST", "GET", "UPLOAD");
        s2_allField.setValue("S2-ALL");
        s2_allField.getItems().addAll("S2-ALL",
                "S2-001", "S2-005", "S2-007", "S2-008",
                "S2-009", "S2-012", "S2-013", "S2-015", "S2-016", "S2-019",
                "S2-Dev", "S2-032", "S2-037", "S2-045", "S2-046"
        );
        String vulurls=vulurl.getText();
        String vulcomboxs=vulcombox.getValue();
        String setEncodeFields=setEncodeField.getValue();
        String methodFields=methodField.getValue();
        String s2_allFields=s2_allField.getValue();
        s2_allField.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> helloinfo.put("useNum", newValue.toLowerCase().replace("s2-", "")));
        helloinfo.put("vulcomboxs",vulcomboxs);
        helloinfo.put("setEncodeFields",setEncodeFields);
        helloinfo.put("methodFields",methodFields);
        helloinfo.put("s2_allFields",s2_allFields);
        helloinfo.put("UA",SettingController.setTing.get("UA"));
        helloinfo.put("Cookie",SettingController.setTing.get("Cookie"));
        helloinfo.put("Proxy",SettingController.setTing.get("Proxy"));

    }


    /**
     * ??????????????????
     */
    @FXML
    void setdic(ActionEvent event) {

        //??????????????????
       String paths = combox.getValue();
       //?????????????????????????????????????????????
        if (paths==""){
            path = "src/main/resources/com/example/javafxdemo/dictionary/spring.txt";
        }else{
            path = "src/main/resources/com/example/javafxdemo/dictionary/"+paths;
        }
        System.out.println(combox.getValue());
    }


    /**
     *?????????????????????
     */

    @FXML
    void stratscan(ActionEvent event) throws Exception {
        //??????url
        String urls = url.getText();
        request re = new request();
        //???????????????????????? post or get
        String requmodes = (String) requmode.getValue();
        System.out.println(requmodes);
        System.out.println(urls);
        //??????????????????
        String paths = combox.getValue();
        //??????????????????url????????????
        if (paths==""||urls.equals("")){
            //??????label????????????
            urllabel.setTextFill(Color.web("#0ea30e"));
            urllabel.setFont(Font.font("Cambria", 15));
            urllabel.setText("?????????url");
            //??????????????????
        }else{
            path = "src/main/resources/com/example/javafxdemo/dictionary/spring.txt";
            String regex = "((http|https)://)([\\w-]+\\.)+[\\w$]+";
            String regStr = "^((http|https)://)([\\w-]+\\.)+[\\w$]+(\\/[\\w-?=&./]*)?$";//[.?*]???????????????????????????
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urls);
            //????????????url???????????????url
            if (matcher.find()){
                System.out.println("???????????????");
                start(urls, requmodes, path);
                String para;
                while ((para = re.msg.poll()) != null) {
                    area.appendText(para + "\r\n");
                    System.out.println(para);
                }
            }else {
                System.out.println("??????????????????");
                urllabel.setText("??????????????????url");
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
        try {
            Connection conn = Jsoup.connect(paratext).timeout(500);
            conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            conn.header("Accept-Encoding", "gzip, deflate, sdch");
            conn.header("Accept-Language", "zh-CN,zh;q=0.8");
            conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
            Document doc= conn.get();
            Elements titles = doc.getElementsByClass("title");
            Elements links = doc.select("a[href]");
            Pattern pattern = Pattern.compile("^([hH][tT]{2}[pP]://|[hH][tT]{2}[pP][sS]://)(([A-Za-z0-9-~]+).)+ ([A-Za-z0-9-~\\/])+$");
            int index = 1;
            for (Element link : links) {
                if(pattern.matcher(link.attr("href")).matches()) {
                    System.out.println("No." + index + " " + link.attr("href"));
                    index++;
                }

            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }


    /**
     *
     *?????????????????????
     */
    @FXML
    public void setthread(ActionEvent actionEvent) throws Exception, InvocationTargetException {

        area.setText("");
        //??????????????????????????????
        proboxone.setProgress(0);
        portCount.set(0);
        proboxlab.setText("");
        //????????????
         paths = combox.getValue();
        //??????url
         urls = url.getText();
        //??????????????????
        requmodes = (String) requmode.getValue();
        uiunit ui=new uiunit(paths,urls,requmodes);
        request re = new request();
        //???????????????
        int threadnum=5;
        int threadboxs=threadbox.getText().equals("")?1:Integer.parseInt(threadbox.getText());
        if (urls.equals("")){
            urllabel.setTextFill(Color.web("#0ea30e"));
            urllabel.setFont(Font.font("Cambria", 15));
            urllabel.setText("?????????url");
        }else{
            //?????????????????????????????????????????????
            if (!threadbox.getText().equals("")) {
                try{
                    threadnum = Integer.parseInt(threadbox.getText());
                    if (threadnum < '0' || threadnum > '9') ;
                }catch (Exception e){
                    threadlabel.setText("???????????????");
                }
            }
            path = "src/main/resources/com/example/javafxdemo/dictionary/"+paths;
            String regex = "((http|https)://)([\\w-]+\\.)+[\\w$]+";//[.?*]???????????????????????????
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urls);
            if (matcher.find()){
                System.out.println("???????????????");
                try {
                    pathpara(path);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int finalI = 0;
                int max = 0;
                max = queuesize;
                request req = new request();
                int finalMax = max;
                td =new Thread(() -> {
                        for (int j = 0; j < threadboxs; j++) {
                            if (td.isInterrupted()) {
                                td.interrupted();
                            }
                            int finalJ = j;

                            for (int i = 1; i <= finalMax; i = i + threadboxs) {
                                String para = req.queue.poll();
                                int finalI1 = i;
                                System.out.println(para);
                                if (requmodes.equals("Get")) {
                                    try {
                                        getHttp(para, urls);
                                    } catch (Exception e) {
                                        System.exit(0);
                                    }
                                } else {
                                    try {
                                        postHttp(para, urls);
                                    } catch (Exception e) {
                                        System.exit(0);
                                    }
                                }
                                proboxone.setProgress((portCount.doubleValue()) / (finalMax-finalI));
                                Platform.runLater(() -> {
                                    proboxlab.setText((""+ Integer.valueOf((int) ((portCount.doubleValue())/(finalMax- finalI)*100))+"%"));//?????????
                                });

                                String paras;
                                while ((paras = req.msg.poll()) != null) {
                                    String finalParas = paras;
                                    Platform.runLater(() -> {
                                        area.appendText(finalParas + "\r\n");
                                    });
                                }
                                portCount.incrementAndGet();
                            }
                            if (portCount.get() == (finalMax-finalI+1)) {//??????????????????
                                portCount.incrementAndGet();
                            }
                        }

                    });
                td.start();
                /*Task tk = new Task<Void>() {
                    @Override public Void call() throws Exception {
                        pathpara(path);
                        final int max = queuesize;
                        request req =new request();
                        for (int i=1;i<=max;i++){
                            String para=req.queue.poll();
                            System.out.println(para);
                            if(requmodes.equals("Get")){
                                try {
                                    getHttp(para,urls);
                                }catch(Exception e){
                                    System.exit(0);
                                }
                            }else{
                                try {
                                    postHttp(para,urls);
                                }catch(Exception e){
                                    System.exit(0);
                                }
                            }
                            //Thread.sleep(500);
                            updateProgress(i,max);
                            String paras;
                            while((paras=req.msg.poll())!=null) {
                                String finalParas = paras;
                                Platform.runLater(() -> {
                                    area.appendText(finalParas + "\r\n");
                                });
                            }
                        }
                        return null;
                    }
                };
                ExecutorService executorService = Executors.newFixedThreadPool(threadnum);
                proboxone.progressProperty().bind(tk.progressProperty());
                executorService.submit(tk);*/

            }else {
                System.out.println("??????????????????");
                urllabel.setText("??????????????????url");
            }
        }
        }

    public void startpro(ActionEvent actionEvent) throws Exception {
        int threadnum=Integer.parseInt(threadbox.getText());
        Taskdemo tk=new Taskdemo();
        Thread td=new Thread(tk);
        ExecutorService executorService = Executors.newFixedThreadPool(threadnum);
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
        //probox.progressProperty().bind(tk.progressProperty());
        //executorService.submit(tk);
        proboxone.progressProperty().bind(tk.progressProperty());
        executorService.submit(tk);
        //new Thread(tk).start();
    }


    public void startportscan(ActionEvent actionEvent) {
            String ips=ip.getText();
            int staports=staport.getText().equals("")?1:Integer.parseInt(staport.getText());
            int endports=endport.getText().equals("")?65535:Integer.parseInt(endport.getText());;
            int threadnums=threadnum.getText().equals("")?1:Integer.parseInt(threadnum.getText());
            String regex = "^(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|[1-9])\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)\\.(1\\d{2}|2[0-4]\\d|25[0-5]|[1-9]\\d|\\d)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(ips);
            if (matcher.find()) {
                for (int j=0;j<threadnums;j++) {
                    int finalJ = j;
                      //tk=new Thread(new PrintThread(ips,j,staports,endports));
                       tk =new Thread(() -> {
                         for (int i = staports+ finalJ; i < endports+1; i=i+threadnums) {
                             if (tk.isInterrupted()){
                                 tk.interrupted();
                                 break;
                             }
                             // ????????????????????????swing??????????????????appendText????????????????????????
                             System.out.println("ip:" + ips + " " + i);
                             try {
                                 Socket sk = new Socket();
                                 sk.connect(new InetSocketAddress(ips, i), 500);
                                 System.out.println("??????" + i + "??????");
                                 //updateProgress(i,max);
                                 int finalI = i;
                                 Platform.runLater(() -> {
                                     showarea.appendText("??????" + finalI + "??????"+"\r\n");
                                 });
                             } catch (Exception e) {
                                 int finalI = i;
                                 //e.printStackTrace();
                                 System.out.println("??????" + finalI + "?????????");
                             }
                             portCount.incrementAndGet();
                         }
                         if (portCount.get()==(endports-staports+1)){//??????????????????
                             portCount.incrementAndGet();
                         }
                     });
                     tk.start();
                }
                showarea.appendText("????????????");

            }

    }

    public void startvulscan(ActionEvent actionEvent) {
        VulTestViewController tk=new VulTestViewController();
        int s2listleng=Payload.s2_list.length;
        Poc.checks cs=new Poc.checks();

        for (int i=0;i<s2listleng;i++){
            vulnums=Payload.s2_list[i];
            Map<String, String> vulInfos = Payload.builder()
                    .selectModelAndVul("poc","s"+vulnums)
                    .VulInfo();
            helloinfo.put("POC"+vulnums,vulInfos.get("payload"));
            helloinfo.put("vulnums",vulnums);
            helloinfo.put("info",vulInfos.get("info"));

            if (!vulnums.equals("045")&&!vulnums.equals("046")) {
                tk.vulcheckall();
            }else{
                tk.check046();
            }

        }
        String para;
        String paras ="";
        while ((para = tk.queue.poll()) != null) {
            area.appendText(para + "\r\n");
            System.out.println(para);
            paras+=para+"\r\n";
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setContentText(paras);
        alert.showAndWait();

    }
}
