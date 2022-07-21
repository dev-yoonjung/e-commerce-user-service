package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.jpa.UserEntity;
import com.ecommerce.user_service.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseUser createUser(RequestUser requestUser) {
        UserEntity userEntity = userRepository.save(UserEntity.of(requestUser));

        return ResponseUser.of(userEntity);
    }

}
