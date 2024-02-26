package com.sparta.project.icylattei.user.dto;

import lombok.Getter;

@Getter
public class LoginSucessDto {

    private String token;
    public LoginSucessDto(String token){
        this.token = token;
    }
}
