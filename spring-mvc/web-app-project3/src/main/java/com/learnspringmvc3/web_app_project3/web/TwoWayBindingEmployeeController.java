package com.learnspringmvc3.web_app_project3.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.learnspringmvc3.web_app_project3.model.Employee;

@Controller
public class TwoWayBindingEmployeeController {

    @GetMapping("/registertwoway")
    public String getHomePage(@ModelAttribute("employee") Employee employee) {
        return "registertwoway";
    }

    @PostMapping("/registertwoway")
    public String registerEmployee(Model model, @ModelAttribute("employee") Employee employee) {
        model.addAttribute("emp", employee);
        return "responsetwoway";
    }

}