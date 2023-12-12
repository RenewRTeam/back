package com.renewr.member.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

public record SignInRequest (

        @NotBlank(message = "이메일은 필수입니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        String password

) {

}