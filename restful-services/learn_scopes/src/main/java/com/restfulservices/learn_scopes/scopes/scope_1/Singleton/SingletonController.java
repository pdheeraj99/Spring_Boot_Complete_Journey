package com.restfulservices.learn_scopes.scopes.scope_1.Singleton;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SingletonController {
    @SuppressWarnings("unused")
    @Autowired
    private SingletonBean singletonBean;

    @SuppressWarnings("unused")
    @Autowired
    private SingletonBean singletonBean1;

    @SuppressWarnings("unused")
    @Autowired
    private SingletonBean singletonBean2;

    // @GetMapping("/test")
    // public String test() {
    // return singletonBean.getMessage();
    // }
}