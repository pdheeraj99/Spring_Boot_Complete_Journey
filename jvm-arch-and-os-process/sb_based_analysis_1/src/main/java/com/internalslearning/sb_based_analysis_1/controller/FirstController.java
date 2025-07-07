package com.internalslearning.sb_based_analysis_1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/")
    public ResponseEntity<String> getMap() {
        return ResponseEntity.ok().body("Hello");
    }

}
