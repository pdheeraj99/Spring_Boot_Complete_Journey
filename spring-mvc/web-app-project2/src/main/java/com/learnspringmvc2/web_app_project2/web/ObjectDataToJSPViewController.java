package com.learnspringmvc2.web_app_project2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.learnspringmvc2.web_app_project2.model.Course;

@Controller
public class ObjectDataToJSPViewController {

    @GetMapping("/course-info")
    public String getCourseInfo(Model model) {
        Course course = new Course(1, "Devops", 1000.0);
        model.addAttribute("course", course);
        return "ObjectDataView";
    }

}
