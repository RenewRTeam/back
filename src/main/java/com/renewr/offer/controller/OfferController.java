package com.renewr.offer.controller;

import com.renewr.global.annotation.CurrentUser;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferMapper mapper;
    private final OfferService offerService;

    //데이터 제공 글 작성하기 (0)
    @PostMapping(value = "/{collectId}" ,consumes = {"multipart/form-data"})
    public BaseResponse<OfferDto.response> postOffer(@PathVariable @RequestPart("collect-id")Long collectId,
                                                       @CurrentUser Long id,
                                                       @RequestPart OfferDto.post post,
                                                       @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Offer offer = offerService.saveOffer(mapper.offerPostDtoToOffer(post),collectId,image,id);
        return new BaseResponse<>(mapper.offerToOfferResponseDto(offer));
    }

    // 특정 데이터 제공 글 확인하기(0)
    @GetMapping("/{offer-id}")
    public BaseResponse<OfferDto.response> getOffer(@PathVariable("offer-id")Long offerId){
        Offer offer = offerService.findOffer(offerId);
        return new BaseResponse<>(mapper.offerToOfferResponseDto(offer));
    }

    //자신이 쓴 데이터 제공글 보기 (0)
    @GetMapping("/myOffers")
    public BaseResponse<List<OfferDto.MyOfferResponse>> getListOffer(@CurrentUser Long id){
        List<Offer> offers =  offerService.findMyOffer(id);
        return new BaseResponse<>(mapper.offerToMyOfferResponseDto(offers));
    }

    //데이터 제공 글 삭제 (o)
    @DeleteMapping("/{offer-id}")
    public BaseResponse<GlobalErrorCode> deleteOffer(@PathVariable("offer-id")Long offerId,
                                                     @CurrentUser Long id){
        offerService.deleteOffer(offerId, id);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    //수집 데이터 관리 탭에 들어갈 때  (0)
    @GetMapping("/col/{collect-id}")
    public BaseResponse<List<OfferDto.OfferImageResponse>> getOfferByCollectId(@PathVariable("collect-id")Long collectId){
        List<Offer> listOffer = offerService.findOfferByCollectId(collectId);
        return new BaseResponse<>(mapper.offerToOfferImageResponseDto(listOffer));
    }
}
