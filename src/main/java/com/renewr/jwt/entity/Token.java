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
    private Long id;

    private Long memId;

    private String accessToken;

    private String refreshToken;

    private int mode;

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    
    public void setMemId(Long memId) {
        this.memId = memId;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

}