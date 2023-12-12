package com.renewr.offer.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.renewr.collection.Entity.DataCollection;
import com.renewr.collect.entity.Collect;
import com.renewr.file.entity.File;
import com.renewr.global.common.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Offer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OFFER_ID")
    private Long id;

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
    @JoinColumn(name = "id")
    @JsonBackReference
    private Collect collect;

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<DataCollection> dataCollections = new ArrayList<>();

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "FILE_ID")
    private File file;

    public void setCollect(Collect collect){
        this.collect = collect;
    }

    public void setOfferStatus(Offer.OfferStatus offerStatus) {
        this.offerStatus = offerStatus;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Builder
    public Offer(String content, String imageUrl, String location){
        this.content = content;
        this.imageUrl = imageUrl;
        this.location = location;
    }


}
