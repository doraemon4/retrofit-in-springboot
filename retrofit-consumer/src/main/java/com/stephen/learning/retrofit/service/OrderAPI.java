package com.stephen.learning.retrofit.service;

import com.stephen.learning.retrofit.model.Order;
import retrofit.http.GET;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 21:38
 * @Description:
 */
public interface OrderAPI {

    @GET("/order/queryAll")
    List<Order> getAllOrders();
}
