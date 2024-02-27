package com.sparta.project.icylattei.user.controller;

import com.sparta.project.icylattei.global.dto.CommonResponseDto;
import com.sparta.project.icylattei.user.dto.requestDto.PasswordUpdateRequest;
import com.sparta.project.icylattei.user.dto.requestDto.ProfileRequest;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.dto.responseDto.ProfileResponse;
import com.sparta.project.icylattei.user.service.UserService;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<CommonResponseDto<String>> signup(
        @Valid @RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED.value())
            .body(new CommonResponseDto<>(HttpStatus.CREATED.value(), "회원가입 성공"));
    }

    @GetMapping("/logout")
    public ResponseEntity<CommonResponseDto<String>> logout(HttpServletRequest request,
        HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response,
            SecurityContextHolder.getContext().getAuthentication());

        return ResponseEntity.status(HttpStatus.OK.value())
            .body(new CommonResponseDto<>(HttpStatus.OK.value(), "로그아웃 성공"));
    }

    @GetMapping
    public ProfileResponse getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return userService.getProfile(userDetails);
    }

    @PutMapping
    public ProfileResponse updateProfile(@AuthenticationPrincipal UserDetailsImpl userDetails,
        @RequestBody ProfileRequest request) {
        return userService.updateProfile(userDetails, request);
    }


}
