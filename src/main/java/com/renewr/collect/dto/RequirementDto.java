package com.renewr.collect.dto;

import com.renewr.collect.entity.Collect;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class RequirementDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Post{
        private String value;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Patch{
        private Long id;
        private String value;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response{
        private Long id;
        private String value;
    }
}
