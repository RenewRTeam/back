package com.renewr.collect.exception;

import com.renewr.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
@Getter
@RequiredArgsConstructor
public enum CollectErrorCode implements ErrorCode {

    COLLECT_NOT_FOUND(HttpStatus.NOT_FOUND, "COLLECT_001", "데이터 수집 글을 찾을 수 없습니다."),
    COLLECT_OWNERSHIP(HttpStatus.FORBIDDEN, "COLLECT_002", "본인만 삭제할 수 있습니다.");

    private final HttpStatus status;

    private final String code;

    private final String message;
}
