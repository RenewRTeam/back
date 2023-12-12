package com.renewr.member.service;

import com.renewr.global.exception.BaseException;
import com.renewr.member.domain.Member;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindService {

    private MemberRepository memberRepository;

    public Member findByMemberId(Long id) {
        return memberRepository.findById(id).orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));
    }

}
