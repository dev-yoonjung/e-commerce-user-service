package com.ecommerce.user_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RequestUser {

    @NotBlank(message = "Email can't be blank")
    @Size(min = 2, message = "Email can't be less than two characters")
    private String email;

    @NotBlank(message = "Name can't be blank")
    @Size(min = 2, message = "Name can't be less than two characters")
    private String name;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, message = "Password must be equal or greater than 8 characters")
    private String password;

}
