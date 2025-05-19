package com.restful_services.rest_api_project1.web.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restful_services.rest_api_project1.service.IGreetingService;

// For the code to understand its rest controller intended for rest api's
@RestController // @Controller + @ResponseBody in each method included
public class GreetingController {

    @Autowired
    private IGreetingService service;

    // Handle GET request to /greet2 endpoint (RECOMMENDED)
    @GetMapping("/greet5")
    public ResponseEntity<String> getWish2() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a response entity with HTTP OK status
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    // Handle GET request to /greet3 endpoint (RECOMMENDED)
    @GetMapping("/greet6")
    // No need to specify @ResponseBody as it's included in @RestController
    public String getWish3() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a response body
        return response;
    }
}