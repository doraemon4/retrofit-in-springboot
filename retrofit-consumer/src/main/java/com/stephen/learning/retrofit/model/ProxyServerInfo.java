package com.stephen.learning.retrofit.model;

import lombok.Data;
import org.springframework.util.StringUtils;

/**
 * @Auther: jack
 * @Date: 2018/9/14 23:06
 * @Description: 代理类
 */
@Data
public class ProxyServerInfo {
    private String ipaddr;
    private int port;
    private String password;
    private String username;
    private String province;
    private boolean https;

    public String getProxyString() {
        StringBuilder sb = new StringBuilder();
        if (this.https) {
            sb.append("https://");
        } else {
            sb.append("http://");
        }

        if (!StringUtils.isEmpty(this.username) && !StringUtils.isEmpty(this.password)) {
            sb.append(this.username).append(':').append(this.password).append('@').append(this.ipaddr).append(':').append(this.port);
        } else {
            sb.append(this.ipaddr).append(":").append(this.port);
        }

        return sb.toString();
    }

    public String getProxyIpString() {
        StringBuilder sb = new StringBuilder();
        if (this.https) {
            sb.append("https://");
        } else {
            sb.append("http://");
        }

        sb.append(this.ipaddr).append(":").append(this.port);
        return sb.toString();
    }
}
