package com.abhilash.linkedin.user_service.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String email;
    private String password;
}
