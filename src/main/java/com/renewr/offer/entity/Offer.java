package com.renewr.offer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renewr.collect.entity.Collect;
import com.renewr.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @Column
    private String content;

    @Column
    private String imageUrl;

    @Column
    private String location;

    @Enumerated(EnumType.STRING)
    @Column
    private Offer.OfferStatus offerStatus = OfferStatus.ATTEND;

    @Getter
    public enum OfferStatus {
        ATTEND ("참여중"),
        REJECTED("거절"),
        APPROVED("승인");


        private final String value;

        OfferStatus(String value) {
            this.value = value;
        }
    }

    @ManyToOne
    @JoinColumn(name = "collectId")
    @JsonBackReference
    private Collect collect;

    public void setCollect(Collect collect){
        this.collect = collect;
    }
    @Builder
    public Offer(String content, String imageUrl, String location){
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
    }
}
