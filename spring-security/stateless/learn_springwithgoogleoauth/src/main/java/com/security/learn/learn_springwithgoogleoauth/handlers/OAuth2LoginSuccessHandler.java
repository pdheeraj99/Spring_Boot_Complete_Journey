package com.security.learn.learn_springwithgoogleoauth.handlers;

import com.security.learn.learn_springwithgoogleoauth.entity.User;
import com.security.learn.learn_springwithgoogleoauth.security.JwtService;
import com.security.learn.learn_springwithgoogleoauth.security.RefreshTokenService;
import com.security.learn.learn_springwithgoogleoauth.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {

    // Create an instance of the smart handler to delegate to
    private final AuthenticationSuccessHandler delegate = new SavedRequestAwareAuthenticationSuccessHandler();

    private final UserService userService;

    private final JwtService jwtService;

    private final RefreshTokenService refreshTokenService; // Add this

    public OAuth2LoginSuccessHandler(UserService userService, JwtService jwtService, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        //  SUCCESSFUL GOOGLE LOGIN FOR USER:
        //  Name: My TV
        //  Email: mytv35049@gmail.com

        System.out.println("SUCCESSFUL GOOGLE LOGIN FOR USER:");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);

        // --> Way 1
        // For now, we will just redirect to a success page ( Hardcoded way )
        // response.sendRedirect("/?login=success");

        // --> Way 2
        // 1. Find or create the user in our database
        User ourUser = userService.findOrCreateUser(email, name);

        // 2. Generate our application's own JWT for this user
        String accessToken = jwtService.generateToken(ourUser);
        String refreshToken = refreshTokenService.createRefreshToken(ourUser.getEmail()).getToken();

        System.out.println("SUCCESSFUL GOOGLE LOGIN FOR USER: " + name);
        System.out.println("GENERATED OUR APP's ACCESS TOKEN: " + accessToken);
        System.out.println("GENERATED OUR APP's REFRESH TOKEN: " + refreshToken);


        // Step 3: Set the tokens in secure, HttpOnly cookies
        addCookie(response, "accessToken", accessToken, 900); // 15 minutes
        addCookie(response, "refreshToken", refreshToken, 604800); // 7 days

        // Here we would set the tokens in secure cookies and then redirect
        // For now, we will just continue with the default redirect behavior

        // 1. Instead of a hardcoded redirect, just call the delegate.
        // 2. It will handle the smart redirect for you. ( meaning when you are trying to access
        //    protected page (/protected something) it will interfere and after everything success, redirects back to
        //    protected page again )

        // Check in notes inside handlers about the scenarios
        // Scenario 1 - for STATEFUL SESSION - to store sessions - doesnt work for SessionCreationPolicy.STATELESS
        delegate.onAuthenticationSuccess(request, response, authentication);

        // Scenario 2 - STATELESS SESSION - for SPA apps -  doesnt work for SessionCreationPolicy.ALWAYS
        // 4. Redirect to protected page or saved request
        // String targetUrl = determineTargetUrl(request);
        // response.sendRedirect(targetUrl);
    }

    private String determineTargetUrl(HttpServletRequest request) {
        // Check if there was a saved request
        HttpSession session = request.getSession(false);
        if (session != null) {
            SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
            if (savedRequest != null) {
                return savedRequest.getRedirectUrl();
            }
        }
        return "/protected"; // Default redirect
    }


    private void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(maxAge);
        // cookie.setSecure(true); // Should be enabled in production (HTTPS)
        response.addCookie(cookie);
    }

}
