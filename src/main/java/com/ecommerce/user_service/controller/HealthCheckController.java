package com.ecommerce.user_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HealthCheckController {

    private final Environment environment;

    @GetMapping("/health-check")
    public String status() {
        return String.format(
                "It's working in user service. " +
                        "local port: %s. " +
                        "server port: %s. " +
                        "token secret: %s. " +
                        "token expiration time: %s. ",
                environment.getProperty("local.server.port"),
                environment.getProperty("server.port"),
                environment.getProperty("token.secret"),
                environment.getProperty("token.expiration_time"));
    }

}
