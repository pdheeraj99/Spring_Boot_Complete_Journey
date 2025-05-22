package com.restful_services.rest_api_project1.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.restful_services.rest_api_project1.model.Student;

@Controller
public class PostGreetingController {

    @PostMapping("/post-add-student")
    @ResponseBody
    public ResponseEntity<String> postAddStudent(@RequestBody Student stu) {
        System.out.println("Student coming from POSTMAN in POST:::" + stu);
        String res = "Student added successfully";
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

}