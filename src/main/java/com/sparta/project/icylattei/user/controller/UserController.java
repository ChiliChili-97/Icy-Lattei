package com.sparta.project.icylattei.user.controller;

import com.sparta.project.icylattei.user.dto.CommonResponseDto;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto> signup(@Valid @RequestBody SignupRequest request){
         userService.signup(request);
         return ResponseEntity.status(HttpStatus.CREATED.value())
             .body(new CommonResponseDto("회원가입 성공", HttpStatus.CREATED.value()));
    }

}
