package com.restful_services.rest_api_project1.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restful_services.rest_api_project1.service.IGreetingService;

@Controller
public class GetGreetingController {

    @Autowired
    private IGreetingService service;

    // Handle GET request to /greet1 endpoint (NOT RECOMMENDED - no responseBody,
    // responseEntity)
    @GetMapping("/greet1")
    public String getWish1() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a view name (not recommended, may cause errors)
        return response;
    }

    // Handle GET request to /greet2 endpoint (RECOMMENDED)
    @GetMapping("/greet2")
    public ResponseEntity<String> getWish2() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a response entity with HTTP OK status
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

    // Handle GET request to /greet3 endpoint (RECOMMENDED)
    @GetMapping("/greet3")
    @ResponseBody
    public String getWish3() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a response body
        return response;
    }

    // Handle GET request to /greet4 endpoint (RECOMMENDED)
    @GetMapping("/greet4")
    @ResponseBody
    public ResponseEntity<String> getWish4() {
        // Generate greeting using the service
        String response = service.generateGreeting();
        // Return the greeting as a response entity with HTTP OK status
        return new ResponseEntity<String>(response, HttpStatus.OK);
    }
}