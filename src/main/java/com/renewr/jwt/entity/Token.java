package com.renewr.jwt.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memId;

    private String accessToken;

    private String refreshToken;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    
    public void setMemId(Long id) {
        this.memId = id;
    }

}