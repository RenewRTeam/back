package com.renewr.collect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renewr.global.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Requirement extends BaseTimeEntity {
    @Id
    @Column(name = "REQUIREMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String value;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id")
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
