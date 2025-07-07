package com.internalslearning.sb_based_analysis_1.customization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletContext;
import jakarta.annotation.PostConstruct;
import java.util.Enumeration;

@Component
public class ServletContextPrinter {

    @Autowired
    private ServletContext servletContext;

    @PostConstruct
    public void printServletContextDetails() {
        System.out.println("=== ServletContext Details ===");
//        System.out.println("Context Path: " + servletContext.getContextPath());
//        System.out.println("Server Info: " + servletContext.getServerInfo());
//        System.out.println("Servlet Context Name: " + servletContext.getServletContextName());
//        System.out.println("Major Version: " + servletContext.getMajorVersion());
//        System.out.println("Minor Version: " + servletContext.getMinorVersion());
//        System.out.println("Virtual Server Name: " + servletContext.getVirtualServerName());

        // Print initialization parameters ( Not Necessary )
//        System.out.println("Initialization Parameters:");
//        Enumeration<String> initParams = servletContext.getInitParameterNames();
//        while (initParams.hasMoreElements()) {
//            String name = initParams.nextElement();
//            System.out.println(name + ": " + servletContext.getInitParameter(name));
//        }

        // Print attributes (e.g., Spring's WebApplicationContext, custom attributes)
        System.out.println("Attributes from ServletContext:");
        Enumeration<String> attributes = servletContext.getAttributeNames();
        while (attributes.hasMoreElements()) {
            String name = attributes.nextElement();
            System.out.println(name + ": " + servletContext.getAttribute(name));
        }

        // Print servlet registrations (e.g., DispatcherServlet)
        System.out.println("Servlet Registrations from ServletContext:");
        servletContext.getServletRegistrations().forEach((name, registration) -> {
            System.out.println("Filter Name: " + name);
            System.out.println("  Class: " + registration.getClassName());
            System.out.println("  URL Patterns: " + registration.getMappings());
        });

        // Print filter registrations (e.g., springSecurityFilterChain)
        System.out.println("Filter Registrations from ServletContext:");
        servletContext.getFilterRegistrations().forEach((name, registration) -> {
            System.out.println("Filter Name: " + name);
            System.out.println("  Class: " + registration.getClassName());
            System.out.println("  URL Patterns: " + registration.getUrlPatternMappings());
        });
    }
}