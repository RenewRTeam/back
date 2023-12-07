package com.renewr.member.service;

import com.renewr.global.exception.BaseException;
import com.renewr.jwt.dto.Token;
import com.renewr.jwt.filter.JwtFilter;
import com.renewr.jwt.provider.TokenProvider;
import com.renewr.member.domain.Authority;
import com.renewr.member.domain.Member;
import com.renewr.member.domain.Password;
import com.renewr.member.dto.SignInRequest;
import com.renewr.member.dto.SignUpRequest;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.renewr.member.domain.Password.ENCODER;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final TokenProvider tokenProvider;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Long signUp(SignUpRequest request) {
        Member member = request.toMember();
        validateDuplicateMember(member.getEmail());

        member.setAuthorities(Authority.USER);
        Member entity = memberRepository.save(member);
        return entity.getId();
    }

    public Token signIn(SignInRequest request) {
        Member member = memberRepository.findByEmail(request.email())
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));

        comparePassword(request.password(), member.getPassword());
        return generateToken(member.getEmail(), request.password());
    }

    private Token generateToken(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        Token token = tokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + token.getAccessToken());

        System.out.println("refreshToken : " + token.getRefreshToken());

        return token;
    }

    private void validateDuplicateMember(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw BaseException.type(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    private void comparePassword(String password, Password memPassword) {
        if(!memPassword.isSamePassword(password, ENCODER)) {
            throw BaseException.type(MemberErrorCode.PASSWORD_MISMATCH);
        }
    }

}
