package com.restfulservices.learn_scopes.scopes.scope_1.Singleton;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// SINGLETON : ( DEFAULT SCOPE - controller, restcontroller, repo, service, component )
// 1. Eager Initialization;
// 2. Only 1 instance of SingletonBean is created

@Component
@Scope("singleton")
public class SingletonBean {
    private String message;

    public SingletonBean() {
        System.out.println("Hello Singleton Bean instance created");
        this.message = "I'm a singleton!";
    }

    public String getMessage() {
        return message;
    }
}
