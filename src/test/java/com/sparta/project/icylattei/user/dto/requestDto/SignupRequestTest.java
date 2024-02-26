package com.sparta.project.icylattei.user.dto.requestDto;

import static org.assertj.core.api.Assertions.*;

import com.sparta.project.icylattei.test.CommonTest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class SignupRequestTest implements CommonTest {

    @DisplayName("회원가입 요청 Dto 생성")
    @Nested
    class createSingupRequestDto {

        @DisplayName("회원가입 요청 Dto 생성 성공")
        @Test
        void createSignupRequestDto_success() {
            // given
            SignupRequest request = new SignupRequest();
            request.setUsername(TEST_USER_NAME);
            request.setPassword(TEST_USER_PASSWORD);

            // when
            Set<ConstraintViolation<SignupRequest>> violations = validate(request);

            // then
            assertThat(violations).isEmpty();
        }


        private Set<ConstraintViolation<SignupRequest>> validate(SignupRequest request){
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            return validator.validate(request);
        }

    }
}