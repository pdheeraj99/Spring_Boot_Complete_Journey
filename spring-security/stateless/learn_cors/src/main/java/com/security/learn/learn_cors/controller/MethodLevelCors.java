package com.security.learn.learn_cors.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
public class MethodLevelCors {
    @GetMapping("/for-everyone")
    public String getPublicData() {
        return "Public data";
    }

    @GetMapping("/for-partner")
    @CrossOrigin(origins = "https://partner-website.com") // Only allows this specific partner
    public String getPartnerData() {
        return "Secret partner data";
    }
}
