package com.ecommerce.user_service.dto;

import com.ecommerce.user_service.jpa.UserEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseUser {

    private String email;

    private String name;

    private String userId;

    private List<ResponseOrder> orders;

    public static ResponseUser of(UserEntity entity) {
        return ResponseUser.builder()
                .email(entity.getEmail())
                .name(entity.getName())
                .userId(entity.getUserId())
                .build();
    }

}
