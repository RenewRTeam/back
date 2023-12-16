package com.renewr.collection.controller;

import com.renewr.collection.Entity.DataCollection;
import com.renewr.collection.dto.DataCollectionDto;
import com.renewr.collection.mapper.DataCollectionMapper;
import com.renewr.collection.repository.DataCollectionRepository;
import com.renewr.global.common.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/collections")
@RequiredArgsConstructor
public class DataCollectionController {
    private final DataCollectionRepository repository;
    private final DataCollectionMapper mapper;
    @GetMapping("/{collect-id}")
    public BaseResponse<List<DataCollectionDto.listResponse>> getOffersByCollectId(@PathVariable("collect-id")Long collectId){
        List<DataCollection> dataCollection = repository.findByCollectId(collectId);
        return new BaseResponse<>(mapper.dataCollectionToDataCollectionDto(dataCollection));
    }
}
