package com.renewr.offer.mapper;

import com.renewr.offer.dto.OfferDto;
import com.renewr.offer.dto.OfferDto.ListResponse;
import com.renewr.offer.entity.Offer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {
    Offer offerPostDtoToOffer(OfferDto.post post);
    OfferDto.response offerToOfferResponseDto(Offer offer);
    List<ListResponse> offerToOfferListResponseDto(List<Offer> offer);

    List<OfferDto.OfferImageResponse> offerToOfferImageResponseDto(List<Offer> offer);
}
