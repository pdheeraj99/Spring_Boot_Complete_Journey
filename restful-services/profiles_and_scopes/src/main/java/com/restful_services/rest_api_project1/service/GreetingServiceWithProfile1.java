package com.restful_services.rest_api_project1.service;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(value = "Profile1")
public class GreetingServiceWithProfile1 implements IGreetingService {

    @Autowired
    private LocalTime time;

    public GreetingServiceWithProfile1() {
        System.out.println("Greeting object with Profile 1 created");
    }

    @Override
    public String generateGreeting() {
        int hour = time.getHour();
        if (hour < 12) {
            return "Good Morning";
        } else if (hour < 16) {
            return "Good Afternoon";
        } else if (hour < 20) {
            return "Good Evening";
        } else {
            return "Good Night";
        }
    }

}
