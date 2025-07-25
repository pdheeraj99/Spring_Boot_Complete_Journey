package com.internalslearning.springsecurityinternals.inspector;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.stereotype.Component;

@Component
public class FilterChainInspector {

    @Autowired
    private FilterChainProxy filterChainProxy;

    @PostConstruct
    public void printFilterChains() {
        System.out.println("Number of security filter chains: " + filterChainProxy.getFilterChains().size());
        filterChainProxy.getFilterChains().forEach(chain -> {
            System.out.println("Chain for: " + chain.toString());
            chain.getFilters().forEach(filter -> System.out.println("  Filter: " + filter.getClass().getName()));
        });
    }
}
