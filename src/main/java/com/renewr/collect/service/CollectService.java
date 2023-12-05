package com.renewr.collect.service;


import com.renewr.collect.entity.Collect;
import com.renewr.collect.repository.CollectRepository;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectService {
    private static CollectRepository collectRepository;

    public Collect saveCollect(Collect collect) {
        Collect newCollect = new Collect(collect.getTitle(),collect.getContent(),
                collect.getImageUrl(),collect.getPoint(),collect.getCapacity());
        return collectRepository.save(newCollect);
    }

    public Collect patchCollect(Collect collect) {
        Collect findCollect = findVerifiedCollect(collect.getCollectId());

        Optional.ofNullable(collect.getTitle())
                .ifPresent(findCollect::setTitle);

        Optional.ofNullable(collect.getContent())
                .ifPresent(findCollect::setContent);

        return collectRepository.save(findCollect);
    }

    public List<Collect> findAllCollect(){
        return collectRepository.findAll();
    }

    public Collect findCollect(Long collectId){
        return findVerifiedCollect(collectId);
    }

    public void deleteCollect(Long collectId){
        collectRepository.deleteById(collectId);
    }

    @Transactional(readOnly = true)
    public Collect findVerifiedCollect(long boastId){
        Optional<Collect> optionalCollect =
                collectRepository.findById(boastId);
        return optionalCollect.orElseThrow(() ->
                new BaseException(GlobalErrorCode.NOT_FOUND));
    }
}
