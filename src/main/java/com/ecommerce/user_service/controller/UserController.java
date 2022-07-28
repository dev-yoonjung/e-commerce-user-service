package com.ecommerce.user_service.controller;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/user-service/users")
@RestController
public class UserController {

    private final UserService userService;

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
    public ResponseEntity<List<ResponseUser>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
