package com.stephen.learning.retrofit.model;

import lombok.Builder;
import lombok.Data;

/**
 * @Auther: jack
 * @Date: 2018/8/29 17:56
 * @Description:
 */
@Data
@Builder
public class Order {
    private String orderId;
    private String produtName;
    private float price;
    private int num;
}
