package com.security.learn.user_service_eureka_client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient // -> Actually, this is not necessary now. By looking at app props spring will add aut...
public class UserServiceEurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceEurekaClientApplication.class, args);
    }

}
