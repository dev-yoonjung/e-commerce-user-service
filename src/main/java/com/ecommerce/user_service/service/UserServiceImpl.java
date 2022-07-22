package com.ecommerce.user_service.service;

import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.jpa.UserEntity;
import com.ecommerce.user_service.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseUser createUser(RequestUser requestUser) {
        UserEntity userEntity = UserEntity.of(requestUser);
        userEntity.encodePassword(passwordEncoder.encode(requestUser.getPassword()));
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return ResponseUser.of(savedUserEntity);
    }

}
