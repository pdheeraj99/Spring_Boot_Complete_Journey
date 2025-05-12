package com.learnspringmvc2.web_app_project2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnspringmvc2.web_app_project2.service.IGreetingService;

@Controller
@RequestMapping("/controller2") // Class Level Mapping
public class MyController2 {

    @Autowired
    private IGreetingService service;

    @GetMapping("/greet3")
    public String generateWish3(Model model) {
        String greeting = service.generateGreeting();
        model.addAttribute("greet", greeting + " 2nd controller response");
        model.addAttribute("meet", " 5PM");
        return "greet";
    }

}
