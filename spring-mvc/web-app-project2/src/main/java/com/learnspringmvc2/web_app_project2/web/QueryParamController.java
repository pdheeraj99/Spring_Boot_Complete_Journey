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
    // Handles GET request to /info1 with required query parameter 'nam'
    // @RequestParam maps query parameter 'nam' to method argument 'name'
    @GetMapping("/info1")
    public String displayMessage1(@RequestParam("nam") String name, Model model) {
        model.addAttribute("info", "Hello! " + name + " Welcome to my Personal Learning");
        return "index";
    }

}
