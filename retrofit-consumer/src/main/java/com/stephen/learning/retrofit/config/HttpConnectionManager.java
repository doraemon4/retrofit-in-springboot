package com.stephen.learning.retrofit.config;

import com.stephen.learning.retrofit.model.ProxyServerInfo;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.net.ssl.SSLContext;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: jack
 * @Date: 2018/8/30 22:24
 * @Description: 使用apache-httpclient调用
 */
@Component
public class HttpConnectionManager {
    private PoolingHttpClientConnectionManager cm = null;

    @PostConstruct
    public void init() {
        LayeredConnectionSocketFactory sslsf = null;
        try {
            sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }


        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslsf)
                .register("http", new PlainConnectionSocketFactory())
                .build();
        cm =new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        //设置整个连接池最大连接数 根据自己的场景决定
        cm.setMaxTotal(200);
        //设置默认路由
        cm.setDefaultMaxPerRoute(20);
    }

    public CloseableHttpClient getHttpClient() {
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();
        //如果不采用连接池就是这种方式获取连接
        //CloseableHttpClient httpClient = HttpClients.createDefault();
        return httpClient;
    }

    public CloseableHttpClient getHttpClient(ProxyServerInfo proxyServerInfo){
        String protocol=proxyServerInfo.isHttps()?"https":"http";
        HttpHost proxy = new HttpHost(proxyServerInfo.getIpaddr(), proxyServerInfo.getPort(),protocol);

        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(proxy.getHostName(), proxy.getPort()),
                new UsernamePasswordCredentials(proxyServerInfo.getUsername(), proxyServerInfo.getPassword()));

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(3000)
                .setConnectionRequestTimeout(3000)
                .setProxy(proxy)
                .build();

        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .setDefaultCredentialsProvider(credentialsProvider)
                .setDefaultRequestConfig(config)
                .build();
        return httpClient;
    }
}
