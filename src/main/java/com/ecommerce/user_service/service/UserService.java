package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;

public interface UserService {

    ResponseUser createUser(RequestUser requestUser);

}
