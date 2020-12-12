package com.li;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : LiWenBo
 * @program : coinexchange
 * @description :
 * @date : 2020-12-12 20:33:55
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CoinCommonApplication {
    public static void main(String[] args) {
        SpringApplication.run(CoinCommonApplication.class, args);
    }
}
