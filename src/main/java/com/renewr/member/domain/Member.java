package com.renewr.member.domain;

import com.renewr.collect.entity.Collect;
import com.renewr.global.common.BaseException;
import com.renewr.global.common.BaseTimeEntity;
// import com.renewr.reward.domain.RewardHistory;
import com.renewr.member.exception.MemberErrorCode;
import com.renewr.offer.entity.Offer;
import com.renewr.reward.domain.RewardHistory;
import com.renewr.reward.dto.RewardOperation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Embedded
    private Password password;

    private String name;

    private int reward;

    private String walletAddress;

    private Authority authority;

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RewardHistory> senders = new ArrayList<>();

    @OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RewardHistory> receivers = new ArrayList<>();

    @OneToMany(mappedBy = "base", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RewardHistory> bases = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private List<Collect> collects = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Offer> offers = new ArrayList<>();

    public Member(String email, Password password, String name, String walletAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.walletAddress = walletAddress;
    }

    public void setAuthorities(Authority authority) {
        this.authority = authority;
    }

    public void updateReward(int amount, RewardOperation operation) {

        if (operation == RewardOperation.ADD) {
            this.reward += amount;
        } else if (operation == RewardOperation.SUBTRACT) {
            this.reward -= amount;
        }
    }

}
