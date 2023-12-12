package com.renewr.member.service;

import com.renewr.global.exception.BaseException;
import com.renewr.member.domain.Member;
import com.renewr.jwt.dto.MemberDetails;
import com.renewr.jwt.dto.UserDetailDto;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Component("userDetailsService")
public class MemberTokenService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));

        return new MemberDetails(new UserDetailDto(
                member.getId(),
                member.getEmail(),
                member.getPassword().getValue(),
                List.of(member.getAuthority().getAuthority())
        ));

    }

}
