package com.ecommerce.user_service.security;

import com.ecommerce.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain configure(
            HttpSecurity http,
            AuthenticationManager authenticationManager,
            UserService userService,
            Environment environment) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(userService, environment);
        authenticationFilter.setAuthenticationManager(authenticationManager);

        http
                .csrf()
                .disable();

        http
                .authorizeHttpRequests()
                .requestMatchers("/actuator/**")
                .permitAll()
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
