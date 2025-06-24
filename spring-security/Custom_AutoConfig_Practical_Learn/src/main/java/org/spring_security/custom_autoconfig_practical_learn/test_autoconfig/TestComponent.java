package org.spring_security.custom_autoconfig_practical_learn.test_autoconfig;

import jakarta.annotation.PostConstruct;
import org.spring_security.custom_autoconfig_practical_learn.custom.MySecurityAwareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestComponent {

    @Autowired(required = false)
    private MySecurityAwareService mySecurityAwareService;

    @PostConstruct
    public void init() {
        if (mySecurityAwareService != null) {
            mySecurityAwareService.performAction();
        } else {
            System.out.println("MySecurityAwareService is not available.");
        }
    }
}