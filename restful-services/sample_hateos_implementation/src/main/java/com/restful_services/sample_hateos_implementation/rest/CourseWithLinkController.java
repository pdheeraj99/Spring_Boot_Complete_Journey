package com.restful_services.sample_hateos_implementation.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful_services.sample_hateos_implementation.model.CourseWithLink;

@RestController
public class CourseWithLinkController {

    @GetMapping("/course-link-info")
    public ResponseEntity<CourseWithLink> getCourseInfo() {
        CourseWithLink course = new CourseWithLink(1, "Devops with AWS", 7999.99);
        Link link = WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder.methodOn(CourseWithLinkController.class).getAllCoursesInfo())
                .withRel("Get All Course");
        course.add(link);
        return new ResponseEntity<CourseWithLink>(course, HttpStatus.OK);
    }

    @GetMapping("/all-courses-info-using-link")
    public ResponseEntity<List<CourseWithLink>> getAllCoursesInfo() {
        CourseWithLink course1 = new CourseWithLink(1, "Devops with AWS", 7999.99);
        CourseWithLink course2 = new CourseWithLink(2, "Spring Boot", 13999.99);
        CourseWithLink course3 = new CourseWithLink(3, "Hibernate", 2199.99);

        List<CourseWithLink> courses = new ArrayList<>();
        courses.add(course1);
        courses.add(course2);
        courses.add(course3);

        return new ResponseEntity<List<CourseWithLink>>(courses, HttpStatus.OK);
    }

}
