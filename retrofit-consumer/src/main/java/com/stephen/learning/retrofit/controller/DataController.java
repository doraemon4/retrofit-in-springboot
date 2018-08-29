package com.stephen.learning.retrofit.controller;

import com.stephen.learning.retrofit.model.Order;
import com.stephen.learning.retrofit.model.User;
import com.stephen.learning.retrofit.service.DataService;
import com.sun.tools.corba.se.idl.constExpr.Or;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 23:07
 * @Description:
 */
@RestController
@RequestMapping("/data")
@Api("远程调用测试")
@CrossOrigin
public class DataController {

    @Autowired
    private DataService dataService;

    @ApiOperation("获取所有用户信息")
    @RequestMapping(value = "/users",method = RequestMethod.POST)
    public List<User> getUsers(){
       return dataService.getUsers();
    }

    @ApiOperation("获取所有订单信息")
    @RequestMapping(value = "/orders",method = RequestMethod.POST)
    public List<Order> getOrders(){
        return dataService.getOrders();
    }
}
