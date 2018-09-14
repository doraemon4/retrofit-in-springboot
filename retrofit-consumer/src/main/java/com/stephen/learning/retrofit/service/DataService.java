package com.stephen.learning.retrofit.service;

import com.stephen.learning.retrofit.model.Order;
import com.stephen.learning.retrofit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 23:02
 * @Description:
 */
@Service
public class DataService {
    @Autowired
    private UserAPI userAPI;
    @Autowired
    private OrderAPI orderAPI;

    /**
     * 远程调用服务
     * @return
     */
    public List<User> getUsers(){
        return userAPI.getAllUsers();
    }

    public List<Order> getOrders(){
        return orderAPI.getAllOrders();
    }
}
