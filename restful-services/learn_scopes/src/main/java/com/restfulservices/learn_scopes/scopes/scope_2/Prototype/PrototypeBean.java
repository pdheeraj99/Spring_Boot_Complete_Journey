package com.restfulservices.learn_scopes.scopes.scope_2.Prototype;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// PROTOTYPE :
// 1. Lazy Initialization
// 2. Prototype scope meaning beans are created every time
// they are requested (requested in terms http request comes to Controller, service, repo etc)

@Component
@Scope("prototype")
public class PrototypeBean {

    public PrototypeBean() {
        System.out.println("Prototype Bean Created");
    }

    public void getMsg() {
        System.out.println("Prototype Bean Called");
    }

}
