package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    ResponseUser createUser(RequestUser requestUser);

    ResponseUser getUserByUserId(String userId);

    List<ResponseUser> getAllUsers();

    ResponseUser updateUser(RequestUser requestUser, String userId);

    void deleteUserByUserId(String userId);
}
