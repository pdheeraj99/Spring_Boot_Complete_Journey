package org.spring_security.internalsunderstandingproject1.debuggers;

import jakarta.servlet.Filter;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SecurityDebugger {

    @EventListener
    public void onApplicationReady(ApplicationReadyEvent event) {
        System.out.println("🔐 Let's see what security filters are active:");

        ApplicationContext context = event.getApplicationContext();
        String[] filterBeans = context.getBeanNamesForType(Filter.class);

        for (String beanName : filterBeans) {
            System.out.println("📋 Filter found: " + beanName);
        }

        // BREAKPOINT 4 - Look at the filters Spring created for you!
        System.out.println("Total filters: " + filterBeans.length);
    }
}