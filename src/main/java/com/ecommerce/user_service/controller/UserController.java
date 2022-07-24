package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    private final Environment environment;

    @GetMapping("/health-check")
    public String status() {
        return String.format(
                "It's working in user service on port %s",
                environment.getProperty("local.server.port"));
    }

    @PostMapping("")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser requestUser) {
        ResponseUser responseUser = userService.createUser(requestUser);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(responseUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUser> getUserByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @GetMapping("")
    public ResponseEntity<List<ResponseUser>> getUserByAll() {
        return ResponseEntity.ok(userService.getUserByAll());
    }

}
