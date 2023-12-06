package com.renewr.collect.service;


import com.renewr.collect.entity.Collect;
import com.renewr.collect.entity.Requirement;
import com.renewr.collect.repository.CollectRepository;
import com.renewr.collect.repository.RequirementRepository;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectService {
    private final CollectRepository collectRepository;
    private final RequirementRepository requirementRepository;

    public Collect saveCollect(Collect collect) {
        Collect newCollect = new Collect(collect.getTitle(),collect.getContent(),
                collect.getImageUrl(),collect.getPoint(),collect.getCapacity());

        //requirement 추가 하는 로직
        List<Requirement> requirements = new ArrayList<>();

        for (Requirement requires : collect.getRequirements()) {
            Requirement newRequirement = new Requirement(requires.getValue());
            newRequirement.setCollect(newCollect);
            requirements.add(newRequirement);
        }

        newCollect.setRequirements(requirements);

        return collectRepository.save(newCollect);
    }

    public Collect patchCollect(Long collectId, Collect collect) {
        //member 패키지 제작 이후, memberId 검증 필요 (본인만 수정 가능 기능 등)
        Collect findCollect = findVerifiedCollect(collectId);

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
        //member 패키지 제작 이후, memberId 검증 필요 (본인만 삭제 가능 기능 등)
        collectRepository.deleteById(collectId);
    }

//    public List<Collect> findMyCollects(long memberId){
//        return collectRepository.findByMemberId(memberId);
//    }

    @Transactional(readOnly = true)
    public Collect findVerifiedCollect(long collectId) {
        return collectRepository.findById(collectId)
                .orElseThrow(() -> new BaseException(GlobalErrorCode.NOT_FOUND));
    }

}
