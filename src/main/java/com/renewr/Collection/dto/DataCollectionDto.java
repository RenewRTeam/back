package com.renewr.Collection.dto;

import com.renewr.collect.dto.RequirementDto;
import com.renewr.collect.entity.Collect;
import com.renewr.offer.dto.OfferDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
