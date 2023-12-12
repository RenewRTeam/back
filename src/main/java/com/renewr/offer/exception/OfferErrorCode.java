package com.renewr.offer.exception;

import com.renewr.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum OfferErrorCode implements ErrorCode {

    OFFER_NOT_FOUND(HttpStatus.NOT_FOUND, "OFFER_001", "데이터 수집 글을 찾을 수 없습니다."),
    OFFER_OWNERSHIP(HttpStatus.FORBIDDEN, "OFFER_002", "본인만 삭제할 수 있습니다."),
    COLLECT_CLOSED(HttpStatus.BAD_REQUEST, "OFFER_003", "이미 마감된 수집 글 입니다.");


    private final HttpStatus status;

    private final String code;

    private final String message;
}
