package com.user.User.dto;

import lombok.Data;

public @Data class UserLoginDTO
{
    private String email;
    private String password;

    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
