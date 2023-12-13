package com.renewr.reward.repository;

import com.renewr.reward.domain.RewardHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RewardRepository extends JpaRepository<RewardHistory, Long> {
}
