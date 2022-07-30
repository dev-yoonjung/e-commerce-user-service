package com.ecommerce.user_service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@EnableWebSecurity
@Configuration
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        AuthenticationFilter authenticationFilter = new AuthenticationFilter();
        authenticationFilter.setAuthenticationManager(authenticationManager);

        http
                .csrf()
                .disable();

        http
                .formLogin()
                .usernameParameter("email");

        http
                .authorizeHttpRequests()
                .requestMatchers("/**")
                .access(new WebExpressionAuthorizationManager("hasIpAddress('127.0.0.1')"))
                .and()
                .addFilter(authenticationFilter);

        http
                .headers()
                .frameOptions()
                .disable();

        return http.build();
    }

}
