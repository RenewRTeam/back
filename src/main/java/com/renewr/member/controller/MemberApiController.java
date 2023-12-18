package com.renewr.member.controller;

import com.renewr.global.annotation.CurrentUser;
import com.renewr.global.common.BaseResponse;
import com.renewr.jwt.entity.Token;
import com.renewr.member.dto.SignInRequest;
import com.renewr.member.dto.SignUpRequest;
import com.renewr.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/sign-out")
    public BaseResponse<Long> signOut(@CurrentUser Long id) {
        return new BaseResponse<>(memberService.signOut(id));
    }

    @PostMapping("/withdrawal")
    public BaseResponse<Long> withdrawal(@CurrentUser Long id) {
        return new BaseResponse<>(memberService.withdrawal(id));
    }

    @GetMapping("/name")
    public BaseResponse<String> getName(@CurrentUser Long id) {
        return new BaseResponse<>(memberService.getName(id));
    }

}
