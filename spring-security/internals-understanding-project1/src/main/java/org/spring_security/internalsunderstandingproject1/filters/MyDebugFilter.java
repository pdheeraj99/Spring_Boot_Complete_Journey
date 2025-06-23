package org.spring_security.internalsunderstandingproject1.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyDebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        System.out.println("🔍 Request coming in: " + httpRequest.getRequestURI());

        // BREAKPOINT 3 - Put this here! This is where we'll see the request
        filterChain.doFilter(request, response);

        System.out.println("✅ Request completed: " + httpRequest.getRequestURI());
    }
}