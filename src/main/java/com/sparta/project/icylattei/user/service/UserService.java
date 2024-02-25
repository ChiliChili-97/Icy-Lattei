package com.sparta.project.icylattei.user.service;

import com.sparta.project.icylattei.jwt.JwtUtil;
import com.sparta.project.icylattei.user.dto.LoginSucessDto;
import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.dto.requestDto.UserRequest;
import com.sparta.project.icylattei.user.entity.Token;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Transactional
    public void signup(SignupRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());
        String nickname = request.getNickname();

        validateUserDuplicate(userRepository.findByUsername(username));
        User user = new User(username, password, nickname, UserRoleEnum.USER);

        userRepository.save(user);
    }

    private void validateUserDuplicate(Optional<User> checkUsername) {
        if (checkUsername.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    @Transactional
    public LoginSucessDto login(UserRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();
        UserRoleEnum role = UserRoleEnum.USER;

        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("등록된 유저가 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        String token = jwtUtil.createToken(request.getUsername(), role);
        LoginSucessDto loginSucessDto = new LoginSucessDto(token);
        return loginSucessDto;
    }

}