package com.learnspringmvc2.web_app_project2.web;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnspringmvc2.web_app_project2.service.IGreetingService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class LearningController {

    @Autowired
    private IGreetingService service;

    @GetMapping("/greet") // Here based on greet it will return the view
    public void generateWish4(Model model) {
        String greeting = service.generateGreeting();
        model.addAttribute("greet", greeting + " learning controller response from Model");
        return; // You didnt return view in that case find the one which is matching with
                // "/greet" from GetMapping in webapp folder
    }

    @GetMapping("/servlet")
    public void generateWish5(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        pw.println("<h1>Response coming from Servlet Method of Controller</h1>");
    }

}
