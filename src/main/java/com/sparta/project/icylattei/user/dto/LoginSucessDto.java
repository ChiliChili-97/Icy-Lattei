package com.sparta.project.icylattei.user.dto;

import com.sparta.project.icylattei.user.entity.Token;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class LoginSucessDto {

    private String token;

    public LoginSucessDto(String token){
        this.token = token;

    }
}
