package com.stephen.learning.retrofit.controller;

import com.google.common.collect.Lists;
import com.stephen.learning.retrofit.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * @Auther: jack
 * @Date: 2018/8/29 20:07
 * @Description:
 */
@RestController
@RequestMapping("/order")
@Api("订单信息相关的APi")
@CrossOrigin
public class OrderController {

    @ApiOperation("获取所有订单信息")
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public List<Order> queryAllOrder(){
        List<Order> orders= Lists.newArrayList();
        orders.add(Order.builder().orderId(UUID.randomUUID().toString()).produtName("篮球").price(120.8f).num(1).build());
        orders.add(Order.builder().orderId(UUID.randomUUID().toString()).produtName("手机").price(3788f).num(1).build());
        orders.add(Order.builder().orderId(UUID.randomUUID().toString()).produtName("衣服").price(120.8f).num(1).build());
        orders.add(Order.builder().orderId(UUID.randomUUID().toString()).produtName("鞋子").price(200f).num(1).build());
        orders.add(Order.builder().orderId(UUID.randomUUID().toString()).produtName("耳机").price(56.8f).num(1).build());
        return orders;
    }
}
