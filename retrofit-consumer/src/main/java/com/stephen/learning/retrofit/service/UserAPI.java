package com.stephen.learning.retrofit.service;

import com.stephen.learning.retrofit.model.User;
import retrofit.http.GET;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 20:26
 * @Description:
 */
public interface UserAPI {
    @GET("/user/queryAll")
    List<User> getAllUsers();
}
