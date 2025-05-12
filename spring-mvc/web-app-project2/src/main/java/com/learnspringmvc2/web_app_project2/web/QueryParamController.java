package com.learnspringmvc2.web_app_project2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QueryParamController {

    // QUERY PARAM - http://localhost:8484/GreetingApp/info?name=Dheeraj
    @GetMapping("/info") // name - Dheeraj
    public String displayMessage(String name, Model model) {
        String response = "Hello! " + name + " Welcome to my Personal Learning";
        model.addAttribute("info", response);
        return "index";
    }

    // Example URL: http://localhost:8484/GreetingApp/info1?nam=Dheeraj
    // 1. Handles GET request to /info1 with required query parameter 'nam'
    // 2. @RequestParam maps query parameter 'nam' to method argument 'name'
    // 3. @RequestParam - Not Compulsory. Used only when query parameter and method
    // argument diff
    @GetMapping("/info1")
    public String displayMessage1(@RequestParam("nam") String name, Model model) {
        model.addAttribute("info", "Hello! " + name + " Welcome to my Personal Learning");
        return "index";
    }

    // Example URL:
    // http://localhost:8484/GreetingApp/info1?nam=Dheeraj&course='Spring'
    // 1. Handles GET request to /info1 with required query parameter 'nam'
    // 2. @RequestParam maps query parameter 'nam' to method argument 'name'
    // 3. @RequestParam - Not Compulsory. Used only when query parameter and method
    // argument diff
    @GetMapping("/info2")
    public String displayMessage2(@RequestParam("nam") String name, String course, Model model) {
        model.addAttribute("info", "Hello! " + name + " Welcome to my Personal Learning of " + course);
        return "index";
    }

}
