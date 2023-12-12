package com.renewr.member.domain;

import com.renewr.global.common.BaseTimeEntity;
import com.renewr.reward.domain.RewardHistory;
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

    public Member(String email, Password password, String name, String walletAddress) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.walletAddress = walletAddress;
    }

    public void setAuthorities(Authority authority) {
        this.authority = authority;
    }

}
