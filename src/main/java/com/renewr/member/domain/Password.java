package com.renewr.member.domain;

import com.renewr.global.exception.BaseException;
import com.renewr.member.exception.MemberErrorCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@RequiredArgsConstructor
@Embeddable
public class Password {

    private static final String PASSWORD_PATTERN = "^(?=.*[a-zA-Z])(?=.*[!@#$%^&*+=-])(?=.*[0-9]).{8,12}$";

    public static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();

    @Column(name = "password", nullable = false)
    private String value;

    private Password(String value) {
        this.value = value;
    }

    public static Password encrypt(String value, PasswordEncoder encoder) {
        validatePassword(value);
        return new Password(encoder.encode(value));
    }

    private static void validatePassword(String value) {
        if (isNotValidPattern(value)) {
            throw BaseException.type(MemberErrorCode.INVALID_PASSWORD_PATTERN);
        }
    }

    private static boolean isNotValidPattern(String password) {
        return !password.matches(PASSWORD_PATTERN);
    }

    public boolean isSamePassword(String password, PasswordEncoder encoder) {
        return encoder.matches(password, this.value);
    }

}
