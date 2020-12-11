package com.li;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description : 授权服务器启动类
 * @date : 2020-12-11 19:59:32
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AuthorizationApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationApplication.class, args);
    }
}
