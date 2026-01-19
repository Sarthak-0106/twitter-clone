package com.sarthak.twitterclone.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterRequest {
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

    public String getEmail(){
        return email;
    }
    public String getPassword(){
        return password;
    }
}
