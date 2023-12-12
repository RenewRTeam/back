package com.renewr.collect.controller;

import com.renewr.collect.dto.CollectDto;
import com.renewr.collect.dto.RequirementDto;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.mapper.CollectMapper;
import com.renewr.collect.service.CollectService;
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
@RequestMapping("/collects")
@RequiredArgsConstructor
public class CollectController {
    private final CollectMapper mapper;
    private final CollectService collectService;

    //(0)
    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<CollectDto.Response> postCollect(@RequestPart CollectDto.Post post,
                                                           @RequestPart (value = "image") MultipartFile image,
                                                           @CurrentUser Long id)
    throws IOException {
        Collect collect = collectService.saveCollect(mapper.collectPostDtoToCollect(post),image,id);
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect), HttpStatus.CREATED);
    }

    @PatchMapping("/{collect-id}")
    public ResponseEntity<CollectDto.Response> patchCollect(@PathVariable("collect-id") Long collectId,
                                       @RequestBody CollectDto.Patch patch){
        Collect collect = collectService.patchCollect(collectId,mapper.collectPatchDtoToCollect(patch));
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect),HttpStatus.OK);
    }

    //(0)
    @GetMapping()
    public ResponseEntity<List<CollectDto.listResponse>> getCollects(){
        List<Collect> listCollects = collectService.findAllCollect();
        return new ResponseEntity<>(mapper.collectToCollectListResponseDto(listCollects),HttpStatus.OK);
    }

    //(0)
    @GetMapping("/{collect-id}")
    public ResponseEntity<CollectDto.Response> getCollect(@PathVariable("collect-id") Long collectId){
        Collect collect = collectService.findCollect(collectId);
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect),HttpStatus.OK);
    }

    @GetMapping("/myCollect") //(0)
    public ResponseEntity<List<CollectDto.listResponse>> getMyCollects(@CurrentUser Long id){
        List<Collect> listMyCollects = collectService.findMyCollects(id);
        return new ResponseEntity<>(mapper.collectToCollectListResponseDto(listMyCollects),HttpStatus.OK);
    }

    //(0)
    @DeleteMapping("/{collect-id}")
    public BaseResponse<GlobalErrorCode> deleteCollect(@PathVariable("collect-id") Long collectId,
                                                       @CurrentUser Long id){
        collectService.deleteCollect(collectId,id);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }

    //데이터 리워드 결정 (0)
    @PostMapping("/allow/{offer-id}")
    public String allowOffer(@PathVariable("offer-id")Long offerId){
        collectService.allowReward(offerId);
        return "OK";
    }
    @PostMapping("/reject/{offer-id}")
    public String rejectOffer(@PathVariable("offer-id")Long offerId){
        collectService.rejectReward(offerId);
        return "OK";
    }
}
