package com.internalslearning.springsecurityinternals.filters;

import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CustomAuthenticationFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String user = request.getHeader("x-user");
        String password = request.getHeader("x-password");
        // ...validate user and password...
        // If valid:
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, resp);
    }
}
