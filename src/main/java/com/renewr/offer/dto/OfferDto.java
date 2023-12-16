package com.renewr.offer.dto;

import com.renewr.collect.dto.CollectDto;
import com.renewr.offer.entity.Offer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class OfferDto {
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class post{
        private Long id;

        private String content;

        private String imageUrl;

        private String location;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class response{

        private Long id;

        private String content;

        private String imageUrl;

        private Offer.OfferStatus offerStatus;

        private CollectDto.ResponseId collect;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListResponse{
        private CollectDto.listResponse listCollect;
        private Offer.OfferStatus offerStatus;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OfferImageResponse{

        private Long id;

        private String imageUrl;

        private String content;

        private String location;

        private Offer.OfferStatus offerStatus;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CollectionResponse{

        private Long id;

        private String content;

        private String imageUrl;

        private Offer.OfferStatus offerStatus;
    }

    @Getter @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MyOfferResponse{

            private Long id;

            private Offer.OfferStatus offerStatus;

            private CollectDto.listResponse collect;
    }
}
