package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/health-check")
    public String status() {
        return "It's working in user service";
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        ResponseUser responseUser = userService.createUser(requestUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }

}
