package com.example.jwtauthentication.controller.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.example.jwtauthentication.util.JWTTokenUtil;

@Component
public class JWTTokenAuthenticationFilter extends OncePerRequestFilter {
    private final JWTTokenUtil tokenUtil;
    private final UserDetailsService userDetailsService;

    public JWTTokenAuthenticationFilter(JWTTokenUtil tokenUtil, UserDetailsService userDetailsService) {
        this.tokenUtil = tokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String JWTToken;

        if (request.getRequestURI().contains("/login") && request.getMethod().equals(HttpMethod.POST.name())) {
            String login = request.getParameter("username");

            JWTToken = tokenUtil.generateToken(userDetailsService.loadUserByUsername(login));
            response.setHeader("Set-Cookie", "token=" + JWTToken + "; " +
                    "SameSite=Strict; " +
//                    "Secure; " + //if u use postman, disable secure flag
                    "HttpOnly; " +
                    "Max-Age=" + 60 * 60 * 24 * 30);
        }

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            Optional<Cookie> token = Arrays.stream(cookies).filter(x -> x.getName().equals("token")).findFirst();
            if (token.isPresent()) {
                JWTToken = token.get().getValue();
                Optional<String> usernameFromToken = tokenUtil.getUsernameFromToken(JWTToken);
                if (usernameFromToken.isPresent()) {
                    UserDetails details = userDetailsService.loadUserByUsername(usernameFromToken.get());
                    if (tokenUtil.validateToken(JWTToken, details)) {
                        UsernamePasswordAuthenticationToken authenticationToken =
                                new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
                        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            }
        }
        filterChain.doFilter(request, response);
    }
}
