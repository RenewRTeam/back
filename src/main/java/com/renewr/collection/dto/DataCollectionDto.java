package com.renewr.collection.dto;

import com.renewr.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class DataCollectionDto {
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class listResponse{
        private long id;
        private OfferDto.OfferImageResponse offer;
    }
}
