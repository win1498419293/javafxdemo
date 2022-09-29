package com.example.javafxdemo.entity;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.web.WebView;

public class uiunit {
    private TextArea area;

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
    private String paths ;

    private String urls ;
    private String requmodes ;

    public uiunit() {
    }

    public TextArea getArea() {
        return area;
    }

    public uiunit(TextArea area, Button scanbut, Button search, ChoiceBox<?> requmode, WebView webview, TextField threadbox, TextField para, Button threadbut, ComboBox<String> combox, TextArea text, TextField url, Label threadlabel, Label urllabel, String path, ProgressBar probox, ProgressBar proboxone, String paths, String urls, String requmodes) {
        this.area = area;
        this.scanbut = scanbut;
        this.search = search;
        this.requmode = requmode;
        this.webview = webview;
        this.threadbox = threadbox;
        this.para = para;
        this.threadbut = threadbut;
        this.combox = combox;
        this.text = text;
        this.url = url;
        this.threadlabel = threadlabel;
        this.urllabel = urllabel;
        this.path = path;
        this.probox = probox;
        this.proboxone = proboxone;
        this.paths = paths;
        this.urls = urls;
        this.requmodes = requmodes;
    }

    public void setArea(TextArea area) {
        this.area = area;
    }

    public Button getScanbut() {
        return scanbut;
    }

    public void setScanbut(Button scanbut) {
        this.scanbut = scanbut;
    }

    public Button getSearch() {
        return search;
    }

    public void setSearch(Button search) {
        this.search = search;
    }

    public ChoiceBox<?> getRequmode() {
        return requmode;
    }

    public void setRequmode(ChoiceBox<?> requmode) {
        this.requmode = requmode;
    }

    public WebView getWebview() {
        return webview;
    }

    public void setWebview(WebView webview) {
        this.webview = webview;
    }

    public TextField getThreadbox() {
        return threadbox;
    }

    public void setThreadbox(TextField threadbox) {
        this.threadbox = threadbox;
    }

    public TextField getPara() {
        return para;
    }

    public void setPara(TextField para) {
        this.para = para;
    }

    public Button getThreadbut() {
        return threadbut;
    }

    public void setThreadbut(Button threadbut) {
        this.threadbut = threadbut;
    }

    public ComboBox<String> getCombox() {
        return combox;
    }

    public void setCombox(ComboBox<String> combox) {
        this.combox = combox;
    }

    public TextArea getText() {
        return text;
    }

    public void setText(TextArea text) {
        this.text = text;
    }

    public TextField getUrl() {
        return url;
    }

    public void setUrl(TextField url) {
        this.url = url;
    }

    public Label getThreadlabel() {
        return threadlabel;
    }

    public void setThreadlabel(Label threadlabel) {
        this.threadlabel = threadlabel;
    }

    public Label getUrllabel() {
        return urllabel;
    }

    public void setUrllabel(Label urllabel) {
        this.urllabel = urllabel;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProgressBar getProbox() {
        return probox;
    }

    public void setProbox(ProgressBar probox) {
        this.probox = probox;
    }

    public ProgressBar getProboxone() {
        return proboxone;
    }

    public void setProboxone(ProgressBar proboxone) {
        this.proboxone = proboxone;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getRequmodes() {
        return requmodes;
    }

    public void setRequmodes(String requmodes) {
        this.requmodes = requmodes;
    }
}
