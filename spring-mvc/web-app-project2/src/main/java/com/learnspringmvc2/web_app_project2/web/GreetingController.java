package com.learnspringmvc2.web_app_project2.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learnspringmvc2.web_app_project2.service.IGreetingService;

@Controller
@RequestMapping("/controller1") // Class Level Mapping
public class GreetingController {

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
        model.addAttribute("greet", greeting + "1st controller response from Model");
        return "greet"; // return greet view
    }

    // Map also can be used instead of Model
    @GetMapping("/greet4")
    public String generateWish4(Map<String, Object> map) {
        String greeting = service.generateGreeting();
        map.put("greet", greeting + "1st controller response from Map");
        return "greet"; // return greet view
    }

}
