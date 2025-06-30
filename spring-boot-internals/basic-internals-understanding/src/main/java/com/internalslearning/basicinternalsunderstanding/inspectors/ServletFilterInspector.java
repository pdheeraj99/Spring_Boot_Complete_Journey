package com.internalslearning.basicinternalsunderstanding.inspectors;

import jakarta.annotation.PostConstruct;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;

@Component
public class ServletFilterInspector {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ApplicationContext applicationContext;

    @PostConstruct
    public void inspectServletsAndFilters() {
        // List all servlets in ServletContext
        System.out.println("=== Servlets in ServletContext ===");
        Map<String, ? extends ServletRegistration> servletRegistrations = servletContext.getServletRegistrations();
        if (servletRegistrations.isEmpty()) {
            System.out.println("No servlets found (yet).");
        } else {
            servletRegistrations.forEach((name, reg) -> {
                System.out.println("Servlet Name: " + name + ", Class: " + reg.getClassName());
            });
        }

        // List all filters in ServletContext
        System.out.println("=== Filters in ServletContext ===");
        Map<String, ? extends FilterRegistration> filterRegistrations = servletContext.getFilterRegistrations();
        if (filterRegistrations.isEmpty()) {
            System.out.println("No filters found.");
        } else {
            filterRegistrations.forEach((name, reg) -> {
                System.out.println("Filter Name: " + name + ", Class: " + reg.getClassName());
            });
        }

        // List Spring Security's FilterChainProxy and its filters with bean name
        System.out.println("=== Spring Security FilterChainProxy ===");
        String filterChainProxyBeanName = "springSecurityFilterChain";
        if (applicationContext.containsBean(filterChainProxyBeanName)) {
            FilterChainProxy filterChainProxy = applicationContext.getBean(filterChainProxyBeanName, FilterChainProxy.class);
            System.out.println("FilterChainProxy Bean Name: " + filterChainProxyBeanName + ", Class: " + filterChainProxy.getClass().getName());
            System.out.println("=== Security Filters in FilterChainProxy ===");
            for (SecurityFilterChain chain : filterChainProxy.getFilterChains()) {
                chain.getFilters().forEach(filter -> {
                    System.out.println("Security Filter: " + filter.getClass().getName());
                });
            }
        } else {
            System.out.println("No FilterChainProxy found (Spring Security not enabled)");
        }

        // List all beans in Spring Context with their names and classes, skipping self
        System.out.println("=== All Beans in Spring Context ===");
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanNames); // Sort for readability
        for (String beanName : beanNames) {
            if (beanName.equals("servletFilterInspector")) {
                System.out.println("Bean Name: " + beanName + ", Class: com.internalslearning.basicinternalsunderstanding.inspectors.ServletFilterInspector (skipped due to self-reference)");
                continue;
            }
            try {
                Object bean = applicationContext.getBean(beanName);
                System.out.println("Bean Name: " + beanName + ", Class: " + bean.getClass().getName());
            } catch (Exception e) {
                System.out.println("Bean Name: " + beanName + ", Class: [Failed to retrieve - " + e.getMessage() + "]");
            }
        }
    }
}