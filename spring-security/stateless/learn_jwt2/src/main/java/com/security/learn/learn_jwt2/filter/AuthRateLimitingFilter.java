package com.security.learn.learn_jwt2.filter;

import com.security.learn.learn_jwt2.service.RateLimitService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
public class AuthRateLimitingFilter extends OncePerRequestFilter {
    private final RateLimitService limiter;

    public AuthRateLimitingFilter(RateLimitService limiter) {
        this.limiter = limiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain)
            throws ServletException, IOException {
        if (req.getRequestURI().startsWith("/api/auth/")) {
            String clientKey = req.getRemoteAddr();    // or req.getHeader("X-Forwarded-For")
            if (!limiter.tryConsume(clientKey)) {
                res.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
                res.getWriter().write("Too many requests – try again later");
                return;
            }
        }
        chain.doFilter(req, res);
    }
}
