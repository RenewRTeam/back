package com.renewr.collect.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.renewr.global.common.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@DynamicUpdate
@NoArgsConstructor
public class Collect extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long collectId;

    @Column
    @NotBlank(message = "제목을 입력해 주세요")
    private String title;

    @Column
    @NotBlank(message = "내용을 입력해 주세요.")
    private String content;

    @Column
    private String imageUrl;

    @Column
    @NotNull(message = "리워드를 입력해 주세요.")
    private int point;

    @Column
    @NotNull(message = "마감 인원을 설정해 주세요.")
    private int capacity;

    @Enumerated(EnumType.STRING)
    @Column
    private CollectStatus status = CollectStatus.IN_PROGRESS;

    @Getter
    public enum CollectStatus {
        IN_PROGRESS("진행중"),
        CLOSED("마감");

        private final String value;

        CollectStatus(String value) {
            this.value = value;
        }
    }

    @OneToMany(mappedBy = "collect", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Requirement> requirements = new ArrayList<>();

    public Collect(String title, String content, String imageUrl , int point, int capacity){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.point = point;
        this.capacity = capacity;
    }

}
