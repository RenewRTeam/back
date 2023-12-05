package com.renewr.collect.dto;

import com.renewr.collect.entity.Collect;
import com.renewr.requirements.dto.RequirementDto;
import com.renewr.requirements.entity.Requirement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

//        @NotNull(message = "마감일을 입력해 주세요.")
//        private Date deadLine;

        @NotNull(message = "마감 인원을 설정해 주세요.")
        private int capacity;

        private List<RequirementDto.Post> requirements;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private Long collectId;

        private String title;

        private String content;

        private String imageUrl;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private long collectId;

        private String title;

        private String content;

        private String imageUrl;

        private int point;

        private int capacity;

        private Collect.CollectStatus status;

        private List<Requirement> requirements;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class listResponse{
        private long collectId;

        private String title;

        private int capacity;

        private String imageUrl;
    }
}
