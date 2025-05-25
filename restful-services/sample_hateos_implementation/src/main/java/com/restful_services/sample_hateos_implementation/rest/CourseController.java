package com.restful_services.sample_hateos_implementation.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful_services.sample_hateos_implementation.model.Course;

@RestController
public class CourseController {

    @GetMapping("/course-info")
    public ResponseEntity<Course> getCourseInfo() {
        Course course = new Course(1, "Devops with AWS", 7999.99);
        return new ResponseEntity<Course>(course, HttpStatus.OK);
    }

    @GetMapping("/all-courses-info")
    public ResponseEntity<List<Course>> getAllCoursesInfo() {
        Course course1 = new Course(1, "Devops with AWS", 7999.99);
        Course course2 = new Course(2, "Spring Boot", 13999.99);
        Course course3 = new Course(3, "Hibernate", 2199.99);

        List<Course> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        return new ResponseEntity<List<Course>>(courses, HttpStatus.OK);
    }

}
