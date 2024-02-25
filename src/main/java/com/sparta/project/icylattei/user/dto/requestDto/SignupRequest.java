package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message =  "아이디는 필수로 입력해야 합니다.")
    @Size(max = 16, message = "아이디는 16자 이하여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Size(max = 16, message = "비밀번호는 16자 이하여야 합니다.")
    private String password;


    private String nickname;

    private String profile;
}

