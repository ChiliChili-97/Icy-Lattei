package com.sparta.project.icylattei.user.controller;

import com.sparta.project.icylattei.jwt.JwtUtil;
import com.sparta.project.icylattei.user.dto.CommonResponseDto;
import com.sparta.project.icylattei.user.dto.LoginSucessDto;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.dto.requestDto.UserRequest;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.entity.UserRoleEnum.Authority;
import com.sparta.project.icylattei.user.service.UserService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequest request){
         userService.signup(request);
         return ResponseEntity.status(HttpStatus.CREATED.value())
             .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value(), null));
    }



    @PostMapping("/login")
    public ResponseEntity<CommonResponseDto> login(@Valid @RequestBody UserRequest request, HttpServletResponse response){

        LoginSucessDto loginSucessDto = userService.login(request);
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, loginSucessDto.getToken());

        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto("로그인 성공", HttpStatus.OK.value(), null));
    }

}
