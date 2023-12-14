package com.renewr.file.entity;

import com.renewr.collect.entity.Collect;
import com.renewr.offer.entity.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long Id;

    @Column
    private String s3Url;

    @Column
    private String title;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "collect_id")
    private Collect collect;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "offer_id")
    private Offer offer;



    public File(String title, String s3Url) {
        this.title = title;
        this.s3Url = s3Url;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + Id +
                ", title='" + title + '\'' +
                ", s3Url='" + s3Url + '\'' +
                '}';
    }
}
