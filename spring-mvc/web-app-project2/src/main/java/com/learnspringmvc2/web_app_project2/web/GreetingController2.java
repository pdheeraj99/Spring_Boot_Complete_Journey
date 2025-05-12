package com.learnspringmvc2.web_app_project2.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnspringmvc2.web_app_project2.service.IGreetingService;

@Controller
@RequestMapping("/controller1")
public class GreetingController2 {

    @Autowired
    private IGreetingService service;

    // ----> Option 1 ( ONLY FOR GET )
    // @RequestMapping("/")
    // public ModelAndView generateWish1() {
    // return new ModelAndView();
    // }

    // ----> Option 2 ( FOR GET, Similar for POST, UPDATE, DELETE )
    // @RequestMapping(value = "/greet2", method = RequestMethod.GET)
    // public ModelAndView generateWish2() {
    // return new ModelAndView();
    // }

    // ----> Option 3 ( SPECIFICALLY FOR GET )
    @GetMapping("/greet3")
    public String generateWish3(Model model) {
        String greeting = service.generateGreeting();
        model.addAttribute("greet", greeting);
        return "greet"; // return greet view
    }

}
