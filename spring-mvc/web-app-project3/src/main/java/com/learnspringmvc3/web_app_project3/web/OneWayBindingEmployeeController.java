package com.learnspringmvc3.web_app_project3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnspringmvc3.web_app_project3.model.Employee;

@Controller
public class OneWayBindingEmployeeController {

    @GetMapping("/register")
    public String getHomePage() {

        // 1. Returns the string "register", which is the logical name of the view to
        // render
        // 2. Since @GetMapping is "/register" and this returns "register", it’s clear
        // this method is meant to show the "register" view (e.g., register.jsp or a
        // template). Spring’s view resolver uses this return value to find the actual
        // view file
        return "register";
    }

    @PostMapping("/register")
    public String registerEmployee(Model model, Employee employee) {
        model.addAttribute("emp", employee);
        return "response";
    }

}
