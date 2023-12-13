package com.renewr.member.service;

import com.renewr.global.exception.BaseException;
import com.renewr.member.domain.Member;
import com.renewr.member.dto.Admin;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberFindService {

    private final MemberRepository memberRepository;

    public Member findByMemberId(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));
    }

    public Member findAdmin() {
        return memberRepository.findById(Admin.ADMIN_ID)
                .orElseThrow(() -> BaseException.type(MemberErrorCode.NOT_FOUND_MEMBER));
    }

}
