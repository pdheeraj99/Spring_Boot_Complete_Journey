package com.learnspringmvc1.web_app_project1.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyController1 {

    @RequestMapping("/welcome")
    public ModelAndView displaySomeInfo() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hello! Welcome to our first App 1");
        mav.setViewName("index");

        return mav;

    }

}
