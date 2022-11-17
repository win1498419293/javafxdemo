package com.example.javafxdemo.Controller;

import com.example.javafxdemo.Check.Payload;
import com.example.javafxdemo.Check.Poc;
import com.example.javafxdemo.HelloController;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class FileVulCheckViewTab {
    @FXML
    private Button loadFileID;
    @FXML
    private TableView<HashMap<String, String>> tableViewID;

    @FXML
    private TableColumn<HashMap<String, String>, String> idColumnID;
    //    url
    @FXML
    private TableColumn<HashMap<String, String>, String> urlColumnID;
    //    是否存在漏洞
    @FXML
    private TableColumn<HashMap<String, String>, String> isvulnColumnID;
    @FXML
    private ChoiceBox threadNumID;

    @FXML
    private ListView<String> urlListID;

    @FXML
    private Button unfoldListID;

    @FXML
    private Button regainListID;
    @FXML
    private Button  startCheckID;

    @FXML
    private Button cleanAllID;
    @FXML
    private Button stopCheckID;

    @FXML
    private Button outPutID;

    @FXML
    private Label totalNumID;
    @FXML
    private Label alreadyID;
    @FXML
    private Label unfinishedID;
    @FXML
    private Label activeThreadID;

    @FXML
    private ProgressBar progressID;

    @FXML
    private TextField filePathID;

    @FXML
    private StackPane spListurlID;
    private int alreadyNum=0;

    public static HashMap<String,String> filvulinfo=new HashMap<>();
    public  HashMap<String,String> newbevul=new HashMap<>();



    private final ObservableList<HashMap<String,String>>  data = FXCollections.observableArrayList();
    public  static    List <String>li=null;
    public   String vuls="";

    public void initialize() {
        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void init(){
        filevul();
        setShowUrlList();
        updatadata();
        exporttxt();
        clear();
        threadNumID.setValue(10);
        threadNumID.getItems().addAll(1,10,20,30,40,50);
        threadNumID.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Integer>() {
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int threadnum= (int)threadNumID.getValue();
                }
        });


    }
    @FXML
    public void filevul(){

        this.loadFileID.setOnAction(event ->  {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("请选择文件");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文本类型", "*.txt"));
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("所有类型","*.*"));
            File fe= fileChooser.showOpenDialog(new Stage());
            filePathID.setText(fe.getAbsolutePath());
            try {

                li=readtxt(fe.getAbsolutePath());
                System.out.println(li.size());
                totalNumID.setText(String.valueOf(li.size()));
                urlListID.getItems().addAll(li);
                TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), spListurlID);
                tt.setInterpolator(Interpolator.EASE_OUT);
                tt.setFromX(-100);
                tt.setToX(0);
                tt.play();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public void updatadata(){
        startCheckID.setOnAction(event -> {
            TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), spListurlID);
            alreadyNum=0;
            tt.setInterpolator(Interpolator.EASE_IN);
            tt.setFromX(0);
            tt.setToX(-250);
            tt.play();
            Poc.checks cs = new Poc.checks();
            for (int i = 0; i < li.size(); i++) {
                String vulurls =li.get(i);
                int finalI = i;
                Task tk = new Task<HashMap<String, String>>() {
                    @Override
                    protected void updateValue(HashMap<String, String> value) {
                        alreadyNum+=1;
                        super.updateValue(value);
                        totalNumID.setText(String.valueOf(li.size()));
                        alreadyID.setText(String.valueOf(alreadyNum));
                        unfinishedID.setText(String.valueOf(li.size()-alreadyNum));
                        progressID.setProgress((double) alreadyNum / li.size());
                        stopCheckID.setDisable(true);
                        if (value.size() > 1) {
                            settable(value);
                            newbevul=value;
                        }

                    }

                    @Override
                    protected HashMap<String, String> call() throws Exception {
                        HashMap<String,String> bevul=new HashMap<>();
                        stopCheckID.setDisable(false);
                        try {
                                for (String vulnums : Payload.s2_list) {
                                    Map<String, String> vulInfos = Payload.builder()
                                            .selectModelAndVul("poc", "s" + vulnums)
                                            .VulInfo();
                                    filvulinfo.put("POC" + vulnums, vulInfos.get("payload"));
                                    filvulinfo.put("vulurls", vulurls);
                                    filvulinfo.put("info", vulInfos.get("info"));
                                    filvulinfo.put("UA", HelloController.helloinfo.get("UA"));
                                    filvulinfo.put("Cookie", HelloController.helloinfo.get("Cookie"));
                                    filvulinfo.put("osType", HelloController.helloinfo.get("osType"));
                                    filvulinfo.put("Proxy", SettingController.setTing.get("Proxy"));
                                    if (!vulnums.equals("045") && !vulnums.equals("046")) {
                                        Boolean result = cs.s2001(filvulinfo, vulnums);
                                        if (result) {
                                            vuls+="|"+vulnums;
                                            bevul.put("id", String.valueOf(finalI));
                                            bevul.put("url", vulurls);
                                            bevul.put("num", vuls);
                                        }
                                    }
                                }
                        } catch (Exception e) {
                            System.out.println(e);
                        }
                        return bevul;
                    }

                };
                int threadnum = (int) threadNumID.getValue();
                activeThreadID.setText(String.valueOf(threadNumID.getValue()));
                ExecutorService executorService = Executors.newFixedThreadPool(threadnum);
                executorService.submit(tk);
                stopCheckID.setOnAction(event1 -> tk.cancel());

            }
        });
        }
        private void exporttxt(){
            this.outPutID.setOnAction(event -> {
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("导出");
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("文本类型", "*.txt"));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("所有类型","*.*"));
                fileChooser.setInitialFileName("results");
                File fe= fileChooser.showSaveDialog(new Stage());
                Task tk = new Task<Boolean>() {
                    @Override
                    protected void updateValue(Boolean value) {
                        if (value) {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setContentText("文件导出成功!");
                            alert.show();
                        }

                    }

                    @Override
                    protected Boolean call() throws Exception {
                        if (JFileChooser.APPROVE_OPTION==0) {
                            if (fe != null) {
                                try {
                                    FileWriter fileWriter = new FileWriter(fe.getAbsoluteFile());
                                    BufferedWriter bw = new BufferedWriter(fileWriter);
                                    bw.write(newbevul.get("url") + "      ");
                                    bw.write(String.valueOf(newbevul.get("num")));
                                    bw.close();
                                    return true;
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                        }
                        return false;
                }

            };
                Thread t = new Thread(tk);
                t.start();
        });
        }

    private void setShowUrlList() {
        TranslateTransition tt = new TranslateTransition(Duration.seconds(0.5), spListurlID);
        tt.setInterpolator(Interpolator.EASE_IN);
        unfoldListID.setOnAction(event -> {
            tt.setFromX(-200);
            tt.setToX(0);
            tt.play();
        });
        regainListID.setOnAction(event -> {
            tt.setFromX(0);
            tt.setToX(-250);
            tt.play();
        });
    }
    public List readtxt(String path) throws IOException {
        List <String> strlist=new ArrayList<>();
        InputStreamReader reader =null;
        BufferedReader br =null;
        try{
        File filename = new File(path); // 要读取以上路径的input。txt文件
        reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        String line = "";
        while ((line = br.readLine())!= null) { // 一次读入一行数据
            strlist.add(line);
        }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        HashSet hs=new HashSet<>(strlist);
        strlist.clear();
        strlist.addAll(hs);
        return strlist;
    }
    public void  settable(HashMap<String,String> value){
        data.add(value);
        idColumnID.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("id")));
        urlColumnID.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("url")));
        isvulnColumnID.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get("num")));
        tableViewID.setItems(data);
    }
    private  void clear(){
        this.cleanAllID.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("清除数据");
            alert.setContentText("确定清除?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){    // ... user chose OK
                tableViewID.getItems().clear();
                totalNumID.setText("0");
                alreadyID.setText("0");
                unfinishedID.setText("0");
                activeThreadID.setText("0");
                progressID.setProgress((double) 0);
                filePathID.setText("");
                urlListID.getItems().clear();
                 } else {
                // ... user chose CANCEL or closed the dialog
                }

        });

    }
}
