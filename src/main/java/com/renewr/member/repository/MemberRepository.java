package com.renewr.member.repository;

import com.renewr.member.domain.Member;
import com.renewr.member.domain.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByEmailAndPassword(String email, Password password);

}
