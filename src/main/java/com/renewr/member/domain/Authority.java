package com.renewr.member.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Authority {

    OFFER("OFFER", "데이터 제공자"),
    COLLECTOR("COLLECTOR", "데이터 수집자"),
    ADMIN("ADMIN", "관리자")
    ;

    private final String authority;

    private final String role;

}