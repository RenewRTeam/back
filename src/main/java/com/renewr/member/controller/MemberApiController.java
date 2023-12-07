package com.renewr.member.controller;

import com.renewr.global.common.BaseResponse;
import com.renewr.jwt.dto.Token;
import com.renewr.member.dto.SignInRequest;
import com.renewr.member.dto.SignUpRequest;
import com.renewr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public BaseResponse<Long> signUp(@RequestBody @Valid SignUpRequest request) {
        return new BaseResponse<>(memberService.signUp(request));
    }

    @PostMapping("/sign-in")
    public BaseResponse<Token> signIn(@RequestBody @Valid SignInRequest request) {
        return new BaseResponse<>(memberService.signIn(request));
    }

}
