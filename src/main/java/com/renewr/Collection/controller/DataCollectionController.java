package com.renewr.Collection.controller;

import com.renewr.Collection.Entity.DataCollection;
import com.renewr.Collection.dto.DataCollectionDto;
import com.renewr.Collection.mapper.DataCollectionMapper;
import com.renewr.Collection.repository.DataCollectionRepository;
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
    public ResponseEntity<List<DataCollectionDto.listResponse>> getOffersByCollectId(@PathVariable("collect-id")Long collectId){
        List<DataCollection> dataCollection = repository.findByCollectId(collectId);
        return new ResponseEntity<>(mapper.dataCollectionToDataCollectionDto(dataCollection), HttpStatus.OK);
    }
}
