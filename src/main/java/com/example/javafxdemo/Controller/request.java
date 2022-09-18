package com.example.javafxdemo.Controller;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.web.WebView;
import org.apache.http.HttpHost;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;


import static com.example.javafxdemo.Controller.request.myTM.start;

public class request {

    @FXML
    public static TextArea area;

    @FXML
    private Button scanbut;

    @FXML
    private Button search;

    @FXML
    private ChoiceBox<Object> requmode;

    @FXML
    private WebView webview;

    @FXML
    private TextField threadbox;

    @FXML
    private TextField para;

    @FXML
    private Button threadbut;

    @FXML
    private ComboBox<?> combox;

    @FXML
    private TextArea text;

    @FXML
    private TextField url;

    @FXML

    public  static Queue<String> queue =new LinkedList<String>();
    public  static Queue<String> msg =new LinkedList<String>();
    private static RequestConfig config = null;//创建请求配置对象
    private static List<String> userAgentList = null;//代理对象集合

    //静态代码块会在类进行加载的时候执行
    static HttpHost proxy = new HttpHost("127.0.0.1", 8080);

    static {
        config = RequestConfig.custom()
                .setConnectTimeout(10000)//设置创建连接超时时间
                .setSocketTimeout(10000)//设置连接超时时间
                .setConnectionRequestTimeout(10000)//设置请求超时时间
               // .setProxy(proxy)//设置代理
                .build();
        userAgentList = new ArrayList<String>();
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:73.0) Gecko/20100101 Firefox/73.0");
        userAgentList.add("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_3) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/13.0.5 Safari/605.1.15");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36 Edge/16.16299");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36");
        userAgentList.add("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
    }


    //创建并返回SSLConnectionSocketFactory对象
    public static SSLConnectionSocketFactory trustHttpsCertificates() throws Exception {
        SSLConnectionSocketFactory socketFactory = null;
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new myTM();
        trustAllCerts[0] = tm;
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, null);
            socketFactory = new SSLConnectionSocketFactory(sc, NoopHostnameVerifier.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return socketFactory;
    }

    public  void  StartThread(String url,int threadnum,String requmodes){
        // 创建一个线程池对象，控制要创建几个线程对象。
        ExecutorService pool = Executors.newFixedThreadPool(threadnum);
        Runnable runnable = new Runnable(){
            @Override
            public void run() {
                System.out.println(url);
                System.out.println(threadnum);
                try {
                    start(url,requmodes);
                    //Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        };
        // 将任务交给线程池管理
        pool.execute(runnable);

    }


    public static class myTM implements TrustManager, X509TrustManager {

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

        public void checkServerTrusted(X509Certificate[] certs, String authType) {

        }

        public void checkClientTrusted(X509Certificate[] certs, String authType) {

        }

        public static void getHttp(String para,String url) throws Exception {
            Registry<ConnectionSocketFactory> registry
                    = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", trustHttpsCertificates())
                    .build();

            PoolingHttpClientConnectionManager connManager = new
                    PoolingHttpClientConnectionManager(registry);
            //配置了HttpClients,创建自定义的httpclient对象
            HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connManager);
            //创建HttpClient对象
            CloseableHttpClient httpClient = builder.build();
            //创建HttpGet请求
            HttpGet httpGet = new HttpGet(url+para);
            RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
            httpGet.setConfig(defaultConfig);
            httpGet.setHeader("User-Agent", userAgentList.get(new Random().nextInt(userAgentList.size())));
            httpGet.setConfig(config);
            httpGet.setHeader("Accept-Encoding", "identity");
            CloseableHttpResponse response = null;
            try {
                //使用HttpClient发起请求
                response = httpClient.execute(httpGet);
                request re =new request();
                //判断响应状态码是否为200
                if (response.getStatusLine().getStatusCode() == 200||response.getStatusLine().getStatusCode() ==403||response.getStatusLine().getStatusCode() ==302) {
                    //如果为200表示请求成功，获取返回数据
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    //打印数据长度
                    //System.out.println(content);
                    //System.out.print(url+para);
                    String showmsg=url+para+"  响应状态码:"+response.getStatusLine().getStatusCode()+"  响应数据包大小:"+response.getEntity().getContentLength();
                    //System.out.println("响应状态码:"+response.getStatusLine().getStatusCode());
                    long len = response.getEntity().getContentLength();
                    msg.offer(showmsg);
                   // System.out.println("响应数据包大小:"+len);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放连接
                if (response == null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    httpClient.close();
                }
            }

        }
        public static void postHttp(String para,String url) throws Exception {
            Registry<ConnectionSocketFactory> registry
                    = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", trustHttpsCertificates())
                    .build();

            PoolingHttpClientConnectionManager connManager = new
                    PoolingHttpClientConnectionManager(registry);
            //配置了HttpClients,创建自定义的httpclient对象
            HttpClientBuilder builder = HttpClients.custom().setConnectionManager(connManager);
            //创建HttpClient对象
            CloseableHttpClient httpClient = builder.build();
            //创建HttpGet请求
            HttpGet httpPost = new HttpGet(url+para);
            RequestConfig defaultConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD).build();
            httpPost.setConfig(defaultConfig);
            httpPost.setHeader("User-Agent", userAgentList.get(new Random().nextInt(userAgentList.size())));
            httpPost.setConfig(config);
            httpPost.setHeader("Accept-Encoding", "identity");
            CloseableHttpResponse response = null;
            try {
                //使用HttpClient发起请求
                response = httpClient.execute(httpPost);
                request re =new request();
                //判断响应状态码是否为200
                if (response.getStatusLine().getStatusCode() == 200||response.getStatusLine().getStatusCode() ==403||response.getStatusLine().getStatusCode() ==302) {
                    //如果为200表示请求成功，获取返回数据
                    String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                    //打印数据长度
                    //System.out.println(content);
                    //System.out.print(url+para);
                    String showmsg=url+para+"  响应状态码:"+response.getStatusLine().getStatusCode()+"  响应数据包大小:"+response.getEntity().getContentLength();
                    //System.out.println("响应状态码:"+response.getStatusLine().getStatusCode());
                    long len = response.getEntity().getContentLength();
                    msg.offer(showmsg);
                    // System.out.println("响应数据包大小:"+len);

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //释放连接
                if (response == null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    httpClient.close();
                }
            }

        }
        public static void pathpara(String path) throws IOException {
            File file = new File(path);
            StringBuilder result = new StringBuilder();
            try{
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));//构造一个BufferedReader类来读取文件

                String s = null;
                while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                    queue.offer(s);
                    //System.out.print(System.lineSeparator() + s);
                }
                br.close();
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public static void start(String url,String requmodes) throws Exception {
            request re=new request();
            String path="src/main/resources/com/example/javafxdemo/spring.txt";
            pathpara(path);
            String para;
            Date today = new Date();
            //String value = (String) re.requmode.getValue();
            //System.out.println(value);
            Date starttime = new Date(today.getTime()+1000*3600*24*30L);
            System.out.println("starttime:"+starttime);
            while((para=queue.poll())!=null){
                if(requmodes.equals("Get")){
                    getHttp(para,url);
                }else{
                    postHttp(para,url);
                }

            }
            Date endday = new Date();
            Date nextMonth = new Date(endday.getTime()+1000*3600*24*30L);
            System.out.println("endtime:"+nextMonth);
        }


        public static void main(String[] args) throws Exception {
            String path="D://Tools/spring.txt";
            //request re = new request();
            pathpara(path);
            String url="https://www.baidu.com/";
            String para;
            while((para=queue.poll())!=null){
                getHttp(para,url);
                //System.out.print(str);

            }
        }
    }
}
