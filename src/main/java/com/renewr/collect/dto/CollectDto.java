package com.renewr.collect.dto;

import com.renewr.collect.entity.Collect;
import com.renewr.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.mapstruct.Mapping;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CollectDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        @NotBlank(message = "제목을 입력해주세요.")
        private String title;

        @NotBlank(message = "내용을 입력해주세요.")
        private String content;

        private String imageUrl;

        @NotNull(message = "리워드를 설정해 주세요.")
        private int point;

        @NotNull(message = "마감 인원을 설정해 주세요.")
        private int capacity;

        private List<RequirementDto.Response> requirements;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private Long id;

        private String title;

        private String content;

        private String imageUrl;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private long id;

        private String title;

        private String content;

        private String imageUrl;

        private int point;

        private int capacity;

        private String userName;

        private Collect.CollectStatus status;

        private List<RequirementDto.Response> requirements;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class listResponse{
        private long id;

        private String title;

        private int capacity;

        private String imageUrl;

        private String userName;
    }
    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ResponseId{
        private long id;

        private String title;
    }
}
