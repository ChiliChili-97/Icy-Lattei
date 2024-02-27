package com.sparta.project.icylattei.user.service;


import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

import com.sparta.project.icylattei.test.UserCommonTest;
import com.sparta.project.icylattei.test.UserTestUtils;
import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DuplicateKeyException;

@ExtendWith(MockitoExtension.class)
class UserServiceTestUser implements UserCommonTest {

    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @DisplayName("회원 가입")
    @Test
    void signup() {
        // given
        var testUser = UserTestUtils.get(TEST_USER);
        given(userRepository.save(any(User.class))).willReturn(testUser);

        // when, then
        assertThatCode(() -> userService.signup(TEST_USER_REQUEST_DTO))
            .doesNotThrowAnyException();

    }

    @DisplayName("회원 가입 실패 - 중복된 사용자")
    @Test
    void signup_fail_duplicateUser() {
        // given
        var testUser = UserTestUtils.get(TEST_USER);
        given(userRepository.findByUsername(ANOTHER_PREFIX + TEST_USER_NAME)).willReturn(
            Optional.of(testUser));

        // when then
        assertThrows(DuplicateKeyException.class,
            () -> userService.signup(TEST_USER_WRONG_REQUEST_DTO));
    }
}

