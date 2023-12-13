package com.renewr.jwt.exception;

import com.renewr.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum JwtErrorCode implements ErrorCode {

    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "JWT_001", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "JWT_002", "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.UNAUTHORIZED, "JWT_003", "지원하지 않는 토큰입니다."),
    ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "JWT_004", "잘못된 요청입니다."),
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;

}