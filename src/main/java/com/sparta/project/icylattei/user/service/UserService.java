package com.sparta.project.icylattei.user.service;

import com.sparta.project.icylattei.user.dto.requestDto.SignupRequest;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;
import com.sparta.project.icylattei.user.repository.UserRepository;
import com.sparta.project.icylattei.userDetails.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    @Transactional
    public void signup(SignupRequest request) {
        String username = request.getUsername();
        String password = passwordEncoder.encode(request.getPassword());
        String nickname = request.getNickname();

       // 중복된 사용자 확인
        validateUserDuplicate(userRepository.findByUsername(username));
        // 사용자 ROLE 확인
        UserRoleEnum role = validateUserRole(request, UserRoleEnum.USER);

        User user = new User(username, password, role, nickname);
        userRepository.save(user);
    }

    private void validateUserDuplicate(Optional<User> checkUsername) {
        if (checkUsername.isPresent()) {
            throw new DuplicateKeyException("중복된 사용자가 존재합니다.");
        }
    }

    private UserRoleEnum validateUserRole(SignupRequest request, UserRoleEnum role){
        if (request.isAdmin()) {
            if (!ADMIN_TOKEN.equals(request.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        return role;

    }

    public void logout(@AuthenticationPrincipal UserDetailsImpl userDetails, SignupRequest request) {
        // 토큰으로 id 가져오기
        String username = userDetails.getUser().getUsername();

        // DB에 접근
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new IllegalArgumentException("선택한 유저가 존재하지 않습니다."));

        if (!username.equals(request.getUsername())){
            throw new IllegalArgumentException("본인만 로그아웃할 수 있습니다.");
        }


    }


}