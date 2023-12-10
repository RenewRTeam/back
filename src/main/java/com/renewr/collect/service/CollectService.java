package com.renewr.collect.service;


import com.renewr.S3.S3Service;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.entity.Requirement;
import com.renewr.collect.repository.CollectRepository;
import com.renewr.collect.repository.RequirementRepository;
import com.renewr.file.entity.File;
import com.renewr.file.service.FileService;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import com.renewr.offer.entity.Offer;
import com.renewr.offer.repository.OfferRepository;
import com.renewr.offer.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CollectService {
    private final CollectRepository collectRepository;
    private final OfferRepository offerRepository;
    private final FileService fileService;
    private final S3Service s3Service;
    public Collect saveCollect(Collect collect,MultipartFile image) throws IOException {

        Collect newCollect = Collect.builder()
                .title(collect.getTitle())
                .content(collect.getContent())
                .imageUrl(collect.getImageUrl())
                .point(collect.getPoint())
                .capacity(collect.getCapacity())
                .build();

        //requirement 추가 하는 로직
        List<Requirement> requirements = new ArrayList<>();

        for (Requirement requirement : collect.getRequirements()) {
            Requirement newRequirement = new Requirement(requirement.getValue());
            newRequirement.setCollect(newCollect);
            requirements.add(newRequirement);
        }

        newCollect.setRequirements(requirements);
        saveCollectFile(image,newCollect);

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

    //수집 데이터 리워드 결정
    public void allowReward(Long offerId){
        Optional<Offer> findOffer = offerRepository.findById(offerId);
        Collect findCollect = findOffer.get().getCollect();
        findOffer.get().setOfferStatus(Offer.OfferStatus.APPROVED);
        findCollect.setHeadCount(findCollect.getHeadCount()+1);
        collectRepository.save(findCollect);
        offerRepository.save(findOffer.get());
        isMaxCapacity(findCollect.getId());
    }
    public void rejectReward(Long offerId){
        Optional<Offer> findOffer = offerRepository.findById(offerId);
        findOffer.get().setOfferStatus(Offer.OfferStatus.REJECTED);
        offerRepository.save(findOffer.get());
    }

    //인원수 마감 기능
    public void isMaxCapacity(Long collectId){
        Collect findCollect = findVerifiedCollect(collectId);
        int maxCapacity = findCollect.getCapacity();
        int currentCapacity = findCollect.getHeadCount();
        if(maxCapacity == currentCapacity){
            findCollect.setStatus(Collect.CollectStatus.CLOSED);
            changeAttendToRejected(collectId);
            collectRepository.save(findCollect);
        }
    }

    //마감했을 때, 대기중인 이미지들 모두 자동 마감처리
    public void changeAttendToRejected(Long collectId) {
        List<Offer> listOffer = offerRepository.findByCollectId(collectId);

        for (Offer offer : listOffer) {
            if (offer.getOfferStatus() == Offer.OfferStatus.ATTEND) {
                offer.setOfferStatus(Offer.OfferStatus.REJECTED);
                offerRepository.save(offer);
            }
        }
    }

    public void saveCollectFile(MultipartFile image, Collect collect) throws IOException {
        String url = s3Service.uploadFile(image); // 이미지 s3에 업로드
        File file = new File(image.getOriginalFilename(), url);
        fileService.save(file); // file repository 저장
        collect.setImageUrl(url);
        collect.setFile(file);
    }

}
