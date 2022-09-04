package com.ecommerce.user_service.service;

import com.ecommerce.user_service.client.OrderServiceClient;
import com.ecommerce.user_service.dto.RequestUser;
import com.ecommerce.user_service.dto.ResponseOrder;
import com.ecommerce.user_service.dto.ResponseUser;
import com.ecommerce.user_service.exception.UserNotFoundException;
import com.ecommerce.user_service.jpa.UserEntity;
import com.ecommerce.user_service.jpa.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final OrderServiceClient orderServiceClient;

    @Override
    @Transactional
    public ResponseUser createUser(RequestUser requestUser) {
        UserEntity userEntity = UserEntity.of(requestUser);
        userEntity.encodePassword(passwordEncoder.encode(requestUser.getPassword()));
        UserEntity savedUserEntity = userRepository.save(userEntity);

        return ResponseUser.of(savedUserEntity);
    }

    @Override
    public ResponseUser getUserByUserId(String userId) {
        ResponseUser responseUser = userRepository.findByUserId(userId)
                .map(ResponseUser::of)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<ResponseOrder> orders = orderServiceClient.getOrders(userId);

        responseUser.setOrders(orders);

        return responseUser;
    }

    @Override
    public ResponseUser getUserByEmail(String email) {
        ResponseUser responseUser = userRepository.findByEmail(email)
                .map(ResponseUser::of)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        List<ResponseOrder> orders = new ArrayList<>();
        responseUser.setOrders(orders);

        return responseUser;
    }

    @Override
    public List<ResponseUser> getAllUsers() {
        return IterableUtils
                .toList(userRepository.findAll())
                .stream()
                .map(ResponseUser::of)
                .toList();
    }

    @Override
    @Transactional
    public ResponseUser updateUser(RequestUser requestUser, String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        userEntity.updateUser(requestUser);
        userEntity.encodePassword(passwordEncoder.encode(requestUser.getPassword()));

        return ResponseUser.of(userEntity);
    }

    @Override
    @Transactional
    public void deleteUserByUserId(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new User(
                userEntity.getEmail(),
                userEntity.getEncryptedPassword(),
                new ArrayList<>());
    }
}
