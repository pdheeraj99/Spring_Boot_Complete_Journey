package org.spring_security.internalsunderstandingproject1.filters;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

// Create this class to catch the filter action
@Component
public class RequestTracker {

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.out.println("🚀 App is ready! Try: http://localhost:8080/hello");
        // BREAKPOINT 2 - Put this here, just to confirm app started
    }
}