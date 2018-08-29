package com.stephen.learning.retrofit.service;

import com.stephen.learning.retrofit.model.Order;
import retrofit.http.GET;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 21:38
 * @Description: 定义访问的接口
 * 注意这里的请求类型和生产者的请求类型一致
 */
public interface OrderAPI {

    @GET("/order/queryAll")
    List<Order> getAllOrders();
}
