package com.sparta.project.icylattei.user.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message =  "아이디는 필수로 입력해야 합니다.")
    @Size(max = 10, message = "아이디는 4자 이상 10자 이하여야 합니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String username;

    @NotBlank(message = "비밀번호는 필수로 입력해야 합니다.")
    @Size(max = 15, message = "비밀번호는 8자 이상 15자 이하여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]{4,10}$")
    private String password;

    @Size(max = 10, message = "nickname은 10자 이하여야 합니다.")
    private String nickname;

}

