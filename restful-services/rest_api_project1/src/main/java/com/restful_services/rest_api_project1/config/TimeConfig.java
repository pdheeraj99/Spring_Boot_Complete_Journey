package com.restful_services.rest_api_project1.config;

import java.time.LocalTime;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeConfig {

    @Bean
    public LocalTime timeBean() {
        return LocalTime.now();
    }

}
