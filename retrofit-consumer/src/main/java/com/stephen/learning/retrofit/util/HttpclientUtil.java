package com.stephen.learning.retrofit.util;

import com.alibaba.fastjson.JSONArray;
import com.stephen.learning.retrofit.config.HttpConnectionManager;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Auther: jack
 * @Date: 2018/8/30 22:37
 * @Description:
 */
@Component
public class HttpclientUtil {
    @Autowired
    HttpConnectionManager connManager;

    /**
     * 发送get请求，返回集合
     * @param url
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<T> get(String url, Class<T> clazz){
        CloseableHttpClient httpClient=connManager.getHttpClient();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse httpResponse=null;
        return getReponse(httpClient,request,httpResponse,clazz);
    }

    /**
     *  发送post请求
     * @param url 请求路径
     * @param queries 请求参数(路径上）
     * @param map   请求体
     * @param headers 请求头
     * @param encoding 编码
     * @param clazz
     * @param <T>
     * @return
     * @throws UnsupportedEncodingException
     */
    public <T> List<T> post(String url,Map<String,String> queries, Map<String,String> map, Map<String,String> headers,
                            String encoding, Class<T> clazz) throws UnsupportedEncodingException {
        CloseableHttpClient httpClient = connManager.getHttpClient();
        //设置请求路径上参数
        StringBuilder sb = new StringBuilder(url);
        if (queries != null && queries.keySet().size() > 0) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        HttpPost request = new HttpPost(sb.toString());

        //装填请求数据
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        request.setEntity(new UrlEncodedFormEntity(nvps, encoding));

        //设置请求头
        if(headers!=null){
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }

        CloseableHttpResponse response = null;
        return getReponse(httpClient,request,response,clazz);
    }

    private <T> List<T> getReponse(CloseableHttpClient httpClient, HttpRequestBase request,CloseableHttpResponse response,Class<T> clazz){
        try {
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = EntityUtils.toString(response.getEntity(), "utf-8");
                return JSONArray.parseArray(result, clazz);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
