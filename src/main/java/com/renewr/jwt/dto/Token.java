package com.renewr.jwt.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Token {

    private String accessToken;

    private String refreshToken;

}