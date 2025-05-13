package com.learnspringmvc2.web_app_project2.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArrayOfObjectstoJSPViewController {

    @GetMapping("/course-objects")
    public String getArrayOfObjects(Model model) {
        String booksName[] = new String[] { "Java", "Spring Boot", "Devops" };
        model.addAttribute("books", booksName);
        return "ArrayOfObjectsView";
    }

}
