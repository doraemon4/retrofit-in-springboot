package com.stephen.learning.retrofit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit.RestAdapter;

/**
 * @Auther: jack
 * @Date: 2018/8/29 20:27
 * @Description:
 */
@Configuration
public class RestAdapterConfig {

    @Bean
    public RestAdapter getRestAdapter(){
        RestAdapter restAdapter=new RestAdapter.Builder().setEndpoint("http://localhost:8090").build();
        return restAdapter;
    }
}
