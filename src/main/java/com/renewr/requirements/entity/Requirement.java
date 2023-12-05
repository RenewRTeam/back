package com.renewr.requirements.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renewr.collect.entity.Collect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
public class Requirement {
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
}
