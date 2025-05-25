package com.restfulservices.learn_scopes.scopes.scope_3.Request;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Request Scope
// For every request of any HTTP method, one new object is created for this class
// Based on the times you will hit this url: you will see that no. of console logs

@RestController
@Scope("request")
public class RequestController {

    public RequestController() {
        System.out.println("New Obj created");
    }

    @GetMapping("/request-test")
    public void test() {
        System.out.println("yes");
    }

}
