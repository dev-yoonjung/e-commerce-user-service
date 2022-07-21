package com.ecommerce.user_service.dto;

import com.ecommerce.user_service.jpa.UserEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseUser {

    private String email;

    private String name;

    private String userId;

    public static ResponseUser of(UserEntity entity) {
        return ResponseUser.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .userId(entity.getUserId())
                .build();
    }

}
