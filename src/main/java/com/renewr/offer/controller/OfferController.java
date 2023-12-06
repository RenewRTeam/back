package com.renewr.offer.controller;

import com.renewr.collect.entity.Collect;
import com.renewr.global.common.BaseResponse;
import com.renewr.global.exception.GlobalErrorCode;
import com.renewr.offer.dto.OfferDto;
import com.renewr.offer.entity.Offer;
import com.renewr.offer.mapper.OfferMapper;
import com.renewr.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferMapper mapper;
    private final OfferService offerService;

    @PostMapping("/{collect-id}")
    public ResponseEntity<OfferDto.response> postOffer(@PathVariable("collect-id")Long collectId, @RequestBody OfferDto.post post){
        Offer offer = offerService.saveOffer(mapper.offerPostDtoToOffer(post),collectId);
        return new ResponseEntity<>(mapper.offerToOfferResponseDto(offer), HttpStatus.CREATED);
    }

    @GetMapping("/{offer-id}")
    public ResponseEntity<OfferDto.response> getOffer(@PathVariable("offer-id")Long offerId){
        Offer offer = offerService.findOffer(offerId);
        return new ResponseEntity<>(mapper.offerToOfferResponseDto(offer),HttpStatus.OK);
    }

//    @GetMapping("/{member-id}")
//    public ResponseEntity<List<OfferDto.ListResponse>> getListOffer(@PathVariable("member-id")Long memberId){
//        List<Offer> offers =  offerService.findMyOffer(memberId);
//        return new ResponseEntity<>(mapper.offerToOfferListResponseDto(offers),HttpStatus.OK);
//    }
    @DeleteMapping("/{offer-id}")
    public BaseResponse<GlobalErrorCode> deleteOffer(@PathVariable("offer-id")Long offerId){
        offerService.deleteOffer(offerId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}
