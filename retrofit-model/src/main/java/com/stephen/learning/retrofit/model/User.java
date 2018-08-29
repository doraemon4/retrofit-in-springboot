package com.stephen.learning.retrofit.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Auther: jack
 * @Date: 2018/8/29 17:51
 * @Description: 用户实体
 */
@Data
@Builder
public class User {
    private String name;
    private String sex;
    private int age;
    private String email;
}
