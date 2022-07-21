package com.ecommerce.user_service.jpa;

import com.ecommerce.user_service.dto.RequestUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

    public static UserEntity of(RequestUser requestUser) {
        return UserEntity.builder()
                .userId(UUID.randomUUID().toString())
                .email(requestUser.getEmail())
                .name(requestUser.getName())
                .encryptedPassword("encrypted_password")
                .build();
    }

}
