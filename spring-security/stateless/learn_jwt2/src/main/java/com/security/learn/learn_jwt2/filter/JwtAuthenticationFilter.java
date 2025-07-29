package com.security.learn.learn_jwt2.filter;

import com.security.learn.learn_jwt2.security.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    // Ee rendu mana helpers.
    // 1. Token ni handle chestundi
    private final JwtService jwtService;
    // 2. User ni DB nunchi testundi
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {

        //  Step 1: Request nunchi 'Authorization' header ni teesko.
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        // Step 2: Header unda leda, 'Bearer ' tho start avutunda leda ani check chey.
        // Okavela lekapothe, ee filter pani em ledu. Request ni next filter ki pampinchi, ikkadnunchi vellipo.
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3: Header correct ga unte, 'Bearer ' (7 characters) tarvata unna token ni extract chey.
        jwt = authHeader.substring(7);

        // Ippudu aa token nunchi user email ni extract chey.
        userEmail = jwtService.extractUsername(jwt);

        // Step 4: Manaki email vachindi, AND ee user ippatike login ayyi ledu ani confirm chesko.
        // (SecurityContextHolder lo authentication null ante, inka login avvaledu ani ardam).
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            // Step 5: User email tho, database nunchi user's full details (password, roles) teesko ra.
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);

            // Step 6: Ippudu, ee token ee user ki sonthamainade na, and adi inka expire avvaleda ani check chey.
            if (jwtService.isTokenValid(jwt, userDetails)) {

                // Step 7: Anni correct ga unte, ee user "authenticated" ani cheppadaniki oka kotha token create chey.
                // Deenilo user details and valla roles (authorities) untayi. Password undadu (null).
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null, // Credentials (password) ikkada avasaram ledu, endukante manam already token verify chesam.
                        userDetails.getAuthorities()
                );

                // Step 8: Ee authentication tho paatu, request yokka konni extra details (IP address laantivi) kuda add chey.
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                // Step 9: Final and most important step. Ee authenticated user ni SecurityContextHolder lo pettu.
                // Ee pani cheyadam valla, Spring Security anukuntundi, "Okay, ee request ki, ee user logged-in."
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 10: Mana pani ayipoindi. Ippudu request ni assembly line lo next filter ki pampinchu.
        filterChain.doFilter(request, response);
    }
}