package com.stephen.learning.retrofit.config;

import com.stephen.learning.retrofit.service.OrderAPI;
import com.stephen.learning.retrofit.service.UserAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import retrofit.RestAdapter;

/**
 * @Auther: jack
 * @Date: 2018/8/29 20:24
 * @Description:
 */
@Configuration
@Import(value = {RestAdapterConfig.class})
public class ApiConfig {
    @Autowired
    private RestAdapter adapter;

    @Bean
    public UserAPI getUserAPI(){
        return adapter.create(UserAPI.class);
    }

    @Bean
    public OrderAPI getOrderAPI(){
        return adapter.create(OrderAPI.class);
    }
}
