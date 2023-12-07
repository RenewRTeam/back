package com.renewr.member.dto;

import javax.validation.constraints.NotBlank;

public record SignInResponse (

        String accessToken,

        String refreshToken

) {

}
