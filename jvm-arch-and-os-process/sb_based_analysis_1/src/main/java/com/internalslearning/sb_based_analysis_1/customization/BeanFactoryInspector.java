package com.internalslearning.sb_based_analysis_1.customization;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletContext;
import java.util.Map;

@Component
public class BeanFactoryInspector {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void inspectServletAndFilterBeans() {
        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getBeanFactory();

        System.out.println("=== BeanFactory Servlet and Filter Inspection from ApplicationContext ===");
        System.out.println("BeanFactory Type: " + beanFactory.getClass().getName());
        System.out.println("BeanDefinition Count: " + beanFactory.getBeanDefinitionCount());

        // Servlet Beans
        System.out.println("\n=== Servlet Beans in ApplicationContext ===");
        Map<String, Servlet> servletBeans = beanFactory.getBeansOfType(Servlet.class);
        if (servletBeans.isEmpty()) {
            System.out.println("No Servlet beans found.");
        } else {
            servletBeans.forEach((beanName, servlet) -> {
                System.out.println("Bean Name: " + beanName);
                System.out.println("  Class: " + servlet.getClass().getName());
                System.out.println("  Instance: " + servlet);
            });
        }

        // Filter Beans
        System.out.println("\n=== Filter Beans in ApplicationContext ===");
        Map<String, Filter> filterBeans = beanFactory.getBeansOfType(Filter.class);
        if (filterBeans.isEmpty()) {
            System.out.println("No Filter beans found.");
        } else {
            filterBeans.forEach((beanName, filter) -> {
                System.out.println("Bean Name: " + beanName);
                System.out.println("  Class: " + filter.getClass().getName());
                System.out.println("  Instance: " + filter);
            });
        }
    }
}