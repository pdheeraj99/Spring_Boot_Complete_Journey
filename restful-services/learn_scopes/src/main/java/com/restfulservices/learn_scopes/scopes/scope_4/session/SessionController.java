package com.restfulservices.learn_scopes.scopes.scope_4.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {
    @Autowired
    private SessionScopedBean sessionScopedBean;

    @GetMapping("/set-username")
    public String setUserName(String userName) {
        sessionScopedBean.setUserName(userName);
        return "Username set to " + userName;
    }

    @GetMapping("/get-username")
    public String getUserName() {
        return sessionScopedBean.getUserName();
    }
}