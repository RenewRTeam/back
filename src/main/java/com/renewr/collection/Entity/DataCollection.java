package com.renewr.collection.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.renewr.collect.entity.Collect;
import com.renewr.offer.entity.Offer;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DataCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COLLECTION_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Collect collect;

    @ManyToOne
    @JoinColumn(name = "OFFER_ID")
    @JsonBackReference
    private Offer offer;

//    @Enumerated(EnumType.STRING)
//    @Column
//    private DataCollection.collectionStates status = collectionStates.ATTEND;
//
//    @Getter
//    public enum collectionStates {
//        ATTEND ("대기중"),
//        APPROVED("승인");
//
//        private final String value;
//
//        collectionStates(String value) {
//            this.value = value;
//        }
//    }

    public void setCollect(Collect collect){
        this.collect = collect;
    }

    public void setOffer(Offer offer){
        this.offer = offer;
    }
    @Builder
    public DataCollection(Collect collect, Offer offer){
        this.collect = collect;
        this.offer = offer;
    }

}
