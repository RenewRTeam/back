package com.renewr.member.exception;

import com.renewr.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements ErrorCode {

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER_001", "이미 등록된 이메일입니다."),
    INVALID_PASSWORD_PATTERN(HttpStatus.BAD_REQUEST, "MEMBER_002", "비밀번호는 영문, 숫자, 특수문자를 포함한 8~12자리여야 합니다."),
    INVALID_EMAIL_OR_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER_003", "이메일 또는 비밀번호가 일치하지 않습니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "MEMBER_004", "비밀번호가 일치하지 않습니다."),
    INVALID_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER_005", "이메일 형식이 올바르지 않습니다."),
    NOT_FOUND_MEMBER(HttpStatus.NOT_FOUND, "MEMBER_006", "회원을 찾을 수 없습니다."),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "MEMBER_007", "잔액이 부족합니다."),
    ;

    private final HttpStatus status;

    private final String code;

    private final String message;

}
