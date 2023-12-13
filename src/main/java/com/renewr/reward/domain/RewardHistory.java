package com.renewr.reward.domain;

import com.renewr.global.common.BaseTimeEntity;
import com.renewr.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "reward_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RewardHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "sender_id", nullable = false)
    private Member sender;

    @ManyToOne(fetch = FetchType.LAZY , cascade = CascadeType.REMOVE)
    @JoinColumn(name = "receiver_id", nullable = false)
    private Member receiver;

    int total;

    int amount;

    @Builder
    public RewardHistory(Member sender, Member receiver, int total, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.total = total;
        this.amount = amount;
    }

}
