package com.renewr.jwt.dto;

import com.renewr.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class UserDetailDto {

    private Long id;

    private String email;

    private String password;

    private List<String> roles;

    public UserDetailDto(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.password = member.getPassword().getValue();
        this.roles = List.of(member.getAuthority().getAuthority());
    }

}
