package com.example.javafxdemo.entity;

import javafx.scene.control.*;

public class uiunit {

    private TextArea area;

    private Button scanbut;

    private Button search;

    private ChoiceBox<?> requmode;

    private TextField threadbox;

    private TextField para;

    private Button threadbut;

    public ComboBox<String> combox;

    private TextArea text;

    private TextField url;

    private Label threadlabel;

    private Label urllabel;

    public String path;

    private ProgressBar probox;

    private ProgressBar proboxone;

    public  String paths ;

    public  String urls ;

    public  String requmodes;
    public  uiunit(){

    }
    public  uiunit(String paths,String urls,String requmodes){
        this.paths=paths;
        this.urls=urls;
        this.requmodes=requmodes;
    }
    public TextArea getarea (){
        return area;
    }
    public void setarea (TextArea area){
        this.area=area;
    }
    public Button getscanbut (){
        return scanbut;
    }
    public void setscanbut (Button scanbut){
        this.scanbut=scanbut;
    }
    public Button getsearch (){
        return search;
    }
    public void setsearch (Button search){
        this.search=search;
    }
    public ChoiceBox<?> getrequmode (){
        return requmode;
    }
    public void setcombox (ChoiceBox<?> requmode){
        this.requmode=requmode;
    }
    public TextField getthreadbox (){
        return threadbox;
    }
    public void setthreadbox (TextField threadbox){
        this.threadbox=threadbox;
    }
    public TextField getpara (){
        return threadbox;
    }
    public void setpara(TextField para){
        this.para=para;
    }
    public Button getthreadbut (){
        return threadbut;
    }
    public void setthreadbut (Button threadbut){
        this.threadbut=threadbut;
    }
    public ComboBox<String> getcombox (){
        return combox;
    }
    public void setthreadbut (ComboBox<String> combox){
        this.combox=combox;
    }
    public TextArea gettext (){
        return text;
    }
    public void settext (TextArea text){
        this.text=text;
    }
    public TextField geturl (){
        return url;
    }
    public void settext (TextField url){
        this.url=url;
    }
    public Label getthreadlabel (){
        return threadlabel;
    }
    public void setthreadlabel (Label url){
        this.threadlabel=threadlabel;
    }
    public Label geturllabel (){
        return urllabel;
    }
    public void seturllabel (Label urllabel){
        this.urllabel=urllabel;
    }
    public ProgressBar getprobox (){
        return probox;
    }
    public void setprobox (ProgressBar probox){
        this.probox=probox;
    }
    public ProgressBar getproboxone (){
        return proboxone;
    }
    public void setproboxone (ProgressBar proboxone){
        this.proboxone=proboxone;
    }
    public String getpaths (){
        return paths;
    }
    public void setpaths (String paths){
        this.paths=paths;
    }
    public String geturls (){
        return urls;
    }
    public void seturls (String urls){
        this.urls=urls;
    }
    public String getrequmodes (){
        return requmodes;
    }
    public void setrequmodes (String requmodes){
        this.requmodes=requmodes;
    }
}
