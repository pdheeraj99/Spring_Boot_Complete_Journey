package com.restful_services.rest_api_project1.web.rest_controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restful_services.rest_api_project1.model.Student;

// Refer Notes 1.md

@RestController
public class StudentController {

    @GetMapping("/stu-mapping")
    public ResponseEntity<Student> registerStudent() {
        // Think of below student is coming from DB
        Student stu = new Student(1, "Dheeraj", "Vizag", 26);

        return new ResponseEntity<Student>(stu, HttpStatus.OK);
    }

    // @RequestBody - used if client is sending data in request body
    @PostMapping("/add-student")
    public ResponseEntity<String> addStudent(@RequestBody Student stu) {
        System.out.println("Student coming from POSTMAN:::" + stu);
        String res = "Student added successfully";
        return new ResponseEntity<String>(res, HttpStatus.CREATED);
    }

}
