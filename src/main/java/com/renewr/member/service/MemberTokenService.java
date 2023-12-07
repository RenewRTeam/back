package com.renewr.member.service;

import com.renewr.global.exception.BaseException;
import com.renewr.member.domain.Member;
import com.renewr.member.domain.Password;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.renewr.member.domain.Password.ENCODER;

@Transactional
@RequiredArgsConstructor
@Component("userDetailsService")
public class MemberTokenService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));

        return org.springframework.security.core.userdetails.User.builder()
                .username(member.getEmail())
                .password(member.getPassword().getValue())
                .roles(member.getAuthority().getAuthority())
                .build();
    }

}
