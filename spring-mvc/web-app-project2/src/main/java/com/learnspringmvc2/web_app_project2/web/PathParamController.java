package com.learnspringmvc2.web_app_project2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PathParamController {

    // QUERY PARAM - http://localhost:8484/GreetingApp/info/Dheeraj/Spring
    @GetMapping("/info/{name}/{course}") // name - Dheeraj , course - Spring
    // 1. Defines the URI template with placeholders {name} and {course} to capture
    // values from the URL path
    // 2. @PathVariable("name") binds the 'name' placeholder from the URI path to
    // this parameter
    // 3. @PathVariable("course") binds the 'course' placeholder from the URI path
    // to this parameter
    public String displayMessage(@PathVariable("name") String name, @PathVariable("course") String course,
            Model model) {
        String response = "Hello! " + name + " Welcome to my Personal Learning of " + course;
        model.addAttribute("info", response);
        return "index";
    }

}
