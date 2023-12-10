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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferMapper mapper;
    private final OfferService offerService;

    //데이터 제공 글 작성하기
    @PostMapping("/{collect-id}")
    public ResponseEntity<OfferDto.response> postOffer(@RequestPart("collect-id")Long collectId,
                                                       @RequestPart OfferDto.post post,
                                                       @RequestPart(value = "image", required = false) MultipartFile image) throws IOException {
        Offer offer = offerService.saveOffer(mapper.offerPostDtoToOffer(post),collectId,image);
        return new ResponseEntity<>(mapper.offerToOfferResponseDto(offer), HttpStatus.CREATED);
    }

    // 특정 데이터 제공 글 확인하기
    @GetMapping("/{offer-id}")
    public ResponseEntity<OfferDto.response> getOffer(@PathVariable("offer-id")Long offerId){
        Offer offer = offerService.findOffer(offerId);
        return new ResponseEntity<>(mapper.offerToOfferResponseDto(offer),HttpStatus.OK);
    }

    //자신이 쓴 데이터 제공글 보기
//    @GetMapping("/{member-id}")
//    public ResponseEntity<List<OfferDto.ListResponse>> getListOffer(@PathVariable("member-id")Long memberId){
//        List<Offer> offers =  offerService.findMyOffer(memberId);
//        return new ResponseEntity<>(mapper.offerToOfferListResponseDto(offers),HttpStatus.OK);
//    }

    //데이터 제공 글 삭제
    @DeleteMapping("/{offer-id}")
    public BaseResponse<GlobalErrorCode> deleteOffer(@PathVariable("offer-id")Long offerId){
        offerService.deleteOffer(offerId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    //수집 데이터 관리 탭에 들어갈 때 사용
//    @GetMapping("/col/{collect-id}")
//    public ResponseEntity<List<OfferDto.OfferImageResponse>> getOfferByCollectId(@PathVariable("collect-id")Long collectId){
//        List<Offer> listOffer = offerService.findOfferByCollectId(collectId);
//        return new ResponseEntity<>(mapper.offerToOfferImageResponseDto(listOffer),HttpStatus.OK);
//    }
}
