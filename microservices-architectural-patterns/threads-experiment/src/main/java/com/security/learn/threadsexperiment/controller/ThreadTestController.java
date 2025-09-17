package com.security.learn.threadsexperiment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadTestController {

    @GetMapping("/slow")
    public String slowEndpoint() throws InterruptedException {
        System.out.println("Slow request started - Thread: " +
                Thread.currentThread().getName());
        Thread.sleep(30000); // 30 seconds wait
        return "Slow response completed!";
    }

    @GetMapping("/fast")
    public String fastEndpoint() {
        System.out.println("Fast request - Thread: " +
                Thread.currentThread().getName());
        return "Fast response!";
    }
}
