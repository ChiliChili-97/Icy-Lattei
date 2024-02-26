package com.sparta.project.icylattei.test;

import com.sparta.project.icylattei.user.entity.User;
import com.sparta.project.icylattei.user.entity.UserRoleEnum;

public interface CommonTest {
    String ANOTHER_PREFIX = "another-";
    Long TEST_USER_ID = 1L;
    Long TEST_ANOTHER_USER_ID = 2L;
    String TEST_USER_NAME = "username";
    String TEST_WRONG_USER_NAME = "Invalid username";
    String TEST_USER_PASSWORD = "password";

    User TEST_USER = User.builder()
        .username(ANOTHER_PREFIX + TEST_USER_NAME)
        .password(ANOTHER_PREFIX + TEST_USER_PASSWORD)
        .role(UserRoleEnum.USER)
        .build();
}
