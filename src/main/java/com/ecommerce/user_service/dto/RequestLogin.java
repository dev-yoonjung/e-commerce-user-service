package com.ecommerce.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestLogin {

    @NotBlank(message = "Email can't be blank")
    @Size(min = 2, message = "Email can't be less than two characters")
    @Email
    private String email;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 8, message = "Password must be equal or greater than 8 characters")
    private String password;

}
