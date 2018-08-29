package com.stephen.learning.retrofit.controller;

import com.google.common.collect.Lists;
import com.stephen.learning.retrofit.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: jack
 * @Date: 2018/8/29 17:59
 * @Description:
 */
@RestController
@RequestMapping("/user")
@Api("用户信息相关的APi")
@CrossOrigin
public class UserController {

    @ApiOperation("获取所有用户信息")
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
    public List<User> getAllUsersInfo(){
        List<User> users= Lists.newArrayList();
        users.add(User.builder().name("jack").sex("男").age(18).email("2132323@qq.com").build());
        users.add(User.builder().name("Bob").sex("男").age(18).email("34324566@qq.com").build());
        users.add(User.builder().name("Peter").sex("男").age(18).email("5667676@qq.com").build());
        users.add(User.builder().name("Alice").sex("女").age(18).email("43434412@qq.com").build());
        users.add(User.builder().name("Mary").sex("女").age(18).email("977867675@qq.com").build());
        return users;
    }
}
