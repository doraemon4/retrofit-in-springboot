package com.stephen.learning.retrofit.util;

import com.alibaba.fastjson.JSONArray;
import com.stephen.learning.retrofit.config.HttpConnectionManager;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/30 22:37
 * @Description:
 */
@Component
public class HttpclientUtil {
    @Autowired
    HttpConnectionManager connManager;

    public <T> List<T> get(String url, Class<T> clazz){
        CloseableHttpClient httpClient=connManager.getHttpClient();
        HttpGet request = new HttpGet(url);
        CloseableHttpResponse httpResponse=null;
        try{
            httpResponse = httpClient.execute(request);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                 String result=EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                 return JSONArray.parseArray(result,clazz);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(httpResponse!=null){
                try{
                    httpResponse.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
