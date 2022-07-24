package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;

import java.util.List;

public interface UserService {

    ResponseUser createUser(RequestUser requestUser);

    ResponseUser getUserByUserId(String userId);

    List<ResponseUser> getUserByAll();

}
