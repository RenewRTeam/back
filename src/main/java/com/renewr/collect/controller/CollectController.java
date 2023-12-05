package com.renewr.collect.controller;

import com.renewr.collect.dto.CollectDto;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.mapper.CollectMapper;
import com.renewr.collect.service.CollectService;
import com.renewr.global.common.BaseResponse;
import com.renewr.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/collects")
@RequiredArgsConstructor
public class CollectController {
    private final CollectMapper mapper;
    private final CollectService collectService;

    @PostMapping()
    public ResponseEntity<CollectDto.Response> postCollect(@RequestBody CollectDto.Post post){
        Collect collect = collectService.saveCollect(mapper.collectPostDtoToCollect(post));
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect), HttpStatus.CREATED);
    }

    @PatchMapping("/{collect-id}")
    public ResponseEntity<CollectDto.Response> patchCollect(@PathVariable("collect-id") Long collectId,
                                       @RequestBody CollectDto.Patch patch){
        Collect collect = collectService.patchCollect(collectId,mapper.collectPatchDtoToCollect(patch));
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CollectDto.listResponse>> getCollects(){
        List<Collect> listCollects = collectService.findAllCollect();
        return new ResponseEntity<>(mapper.collectToCollectListResponseDto(listCollects),HttpStatus.OK);
    }

    @GetMapping("/{collect-id}")
    public ResponseEntity<CollectDto.Response> getCollect(@PathVariable("collect-id") Long collectId){
        Collect collect = collectService.findCollect(collectId);
        return new ResponseEntity<>(mapper.collectToCollectResponseDto(collect),HttpStatus.OK);
    }

//    @GetMapping("/{member-id}")
//    public ResponseEntity<List<CollectDto.listResponse>> getMyCollects(@PathVariable("member-id")Long memberId){
//        List<Collect> listMyCollects = collectService.findMyCollects(memberId);
//        return new ResponseEntity<>(mapper.collectToCollectListResponseDto(listMyCollects),HttpStatus.OK);
//    }

    @DeleteMapping("/{collect-id}")
    public BaseResponse<GlobalErrorCode> deleteCollect(@PathVariable("collect-id") Long collectId){
        collectService.deleteCollect(collectId);
        return new BaseResponse<>(GlobalErrorCode.SUCCESS);
    }
}
