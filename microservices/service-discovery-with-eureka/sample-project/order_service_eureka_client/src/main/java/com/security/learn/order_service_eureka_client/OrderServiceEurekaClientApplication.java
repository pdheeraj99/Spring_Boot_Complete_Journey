package com.security.learn.order_service_eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // -> Actually, this is not necessary now. By looking at app props spring will add aut...
public class OrderServiceEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceEurekaClientApplication.class, args);
    }

}
