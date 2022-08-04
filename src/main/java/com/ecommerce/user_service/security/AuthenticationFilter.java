package com.ecommerce.user_service.security;

import com.ecommerce.user_service.dto.RequestLogin;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

@RequiredArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;

    private final Environment environment;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin credentials = new ObjectMapper()
                    .readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager()
                    .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                credentials.getEmail(),
                                credentials.getPassword(),
                                new ArrayList<>()
                    )
            );
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) {
        User user = (User) authResult.getPrincipal();
        String email = user.getUsername();
        ResponseUser responseUser = userService.getUserByEmail(email);
    }

}
