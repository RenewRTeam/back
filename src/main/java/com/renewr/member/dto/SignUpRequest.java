package com.renewr.member.dto;

import com.renewr.member.domain.Member;
import com.renewr.member.domain.Password;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import static com.renewr.member.domain.Password.ENCODER;

public record SignUpRequest (

        @Size(min = 5, max = 50)
        @NotBlank(message = "이메일은 필수입니다.")
        String email,

        @Size(min = 8, max = 12, message = "비밀번호는 8자 이상, 12자 이하여야 합니다.")
        @NotBlank(message = "비밀번호는 필수입니다.")
        String password,

        @Size(min = 2, max = 10, message = "이름은 2자 이상, 10자 이하여야 합니다.")
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        int mode,

        @NotBlank(message = "지갑 주소는 필수입니다.")
        String walletAddress

) {
        public Member toMember() {
                return new Member(
                        email,
                        Password.encrypt(password, ENCODER),
                        name,
                        walletAddress
                );
        }
}