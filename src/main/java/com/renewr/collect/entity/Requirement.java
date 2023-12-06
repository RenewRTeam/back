package com.renewr.collect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renewr.collect.entity.Collect;
import com.renewr.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Requirement extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requireId;

    @Column
    private String value;

    @ManyToOne
    @JoinColumn(name = "collectId")
    @JsonBackReference
    private Collect collect;

    public Requirement(String value) {
        this.value = value;
    }

    public void setCollect(Collect collect){
        this.collect = collect;
    }

    public void setValue(String value){
        this.value = value;
    }
}
