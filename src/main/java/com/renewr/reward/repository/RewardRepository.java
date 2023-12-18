package com.renewr.reward.repository;

import com.renewr.member.domain.Member;
import com.renewr.reward.domain.RewardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RewardRepository extends JpaRepository<RewardHistory, Long> {
    List<RewardHistory> findByBase(Member base);
}
