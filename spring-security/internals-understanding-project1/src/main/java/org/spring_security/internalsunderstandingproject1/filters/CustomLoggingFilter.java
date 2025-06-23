package org.spring_security.internalsunderstandingproject1.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

// REMEMBER : When we are creating *** Custom Filter *** we need to specify where it needs to be kept
// Custom filter class
@Component
public class CustomLoggingFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(CustomLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // Log request details (example customization)
        logger.info("Request URI: {}, Method: {}", request.getRequestURI(), request.getMethod());

        // Add custom logic here (e.g., token validation, header checks)
        // Example: Check for a custom header
        String customHeader = request.getHeader("X-Custom-Header");
        if (customHeader != null) {
            logger.info("Custom Header Found: {}", customHeader);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }
}