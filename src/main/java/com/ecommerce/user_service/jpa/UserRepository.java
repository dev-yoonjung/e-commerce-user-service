package com.ecommerce.user_service.jpa;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    Optional<UserEntity> findByUserId(String userId);

    void deleteByUserId(String userId);

}
