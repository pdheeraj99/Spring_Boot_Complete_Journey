package org.spring_security.internalsunderstandingproject1.beans;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class SecurityBeansLogger implements CommandLineRunner {

    private final ApplicationContext applicationContext;

    public SecurityBeansLogger(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) {
        String[] beanNames = applicationContext.getBeanDefinitionNames();

        Arrays.stream(beanNames)
                .filter(name -> name.toLowerCase().contains("security"))
                .sorted()
                .forEach(name -> {
                    Object bean = applicationContext.getBean(name);
                    System.out.println("Security Bean: " + name + " --> " + bean.getClass().getName());
                });
    }
}
