package org.spring_security.internalsunderstandingproject1.filters;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterChainInspector {

    @Autowired
    private ApplicationContext context;

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        try {
            // Get the FilterChainProxy (springSecurityFilterChain)
            FilterChainProxy filterChainProxy = context.getBean("springSecurityFilterChain", FilterChainProxy.class);

            List<SecurityFilterChain> chains = filterChainProxy.getFilterChains();

            System.out.println("🔍 SERVLET FILTERS: 6 (what Tomcat sees)");
            System.out.println("🔍 SECURITY FILTER CHAINS: " + chains.size());

            for (SecurityFilterChain chain : chains) {
                List<Filter> filters = chain.getFilters();
                System.out.println("🔗 Security filters in this chain: " + filters.size());

                // BREAKPOINT HERE - inspect the actual 17 filters!
                for (Filter filter : filters) {
                    System.out.println("   → " + filter.getClass().getSimpleName());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}