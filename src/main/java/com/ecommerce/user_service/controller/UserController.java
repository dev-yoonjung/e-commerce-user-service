package com.ecommerce.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/health-check")
    public String status() {
        return "It's working in user service";
    }

}
