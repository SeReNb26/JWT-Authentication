package com.example.jwtauthentication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.example.jwtauthentication.controller.filter.JWTTokenAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTTokenAuthenticationFilter filter;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    public SecurityConfig(JWTTokenAuthenticationFilter filter,
                          UserDetailsService userDetailsService,
                          PasswordEncoder encoder) {
        this.filter = filter;
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/users", "/users/**").hasRole("ADMIN")
                .anyRequest().authenticated()
                    .and()
                .formLogin().permitAll()
                    .and()
                .logout().deleteCookies("token");
    }
}
