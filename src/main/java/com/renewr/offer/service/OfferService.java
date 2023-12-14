package com.renewr.offer.service;

import com.renewr.collection.Entity.DataCollection;
import com.renewr.collection.repository.DataCollectionRepository;
import com.renewr.S3.S3Service;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.service.CollectService;
import com.renewr.file.entity.File;
import com.renewr.file.service.FileService;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import com.renewr.member.service.MemberFindService;
import com.renewr.offer.entity.Offer;
import com.renewr.offer.exception.OfferErrorCode;
import com.renewr.offer.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    private final OfferRepository offerRepository;
    private final CollectService collectService;
    private final DataCollectionRepository dataCollectionRepository;
    private final FileService fileService;
    private final S3Service s3Service;
    private final MemberFindService memberFindService;

    public Offer saveOffer(Offer offer,Long collectId,MultipartFile image,Long id) throws IOException {
        Collect collect = collectService.findVerifiedCollect(collectId);
        if(collect.getStatus() == Collect.CollectStatus.CLOSED){
            throw new BaseException(OfferErrorCode.COLLECT_CLOSED);
        }

        Offer newOffer = Offer.builder()
                .content(offer.getContent())
                .imageUrl(offer.getImageUrl())
                .location(offer.getLocation())
                .member(memberFindService.findByMemberId(id))
                .build();

        collect.addOffers(newOffer);

        DataCollection newCollection = DataCollection.builder()
                .collect(collect)
                .offer(newOffer)
                .build();
        saveOfferFile(image,newOffer);
        dataCollectionRepository.save(newCollection);
        return offerRepository.save(newOffer);
    }

    public void deleteOffer(Long offerId,Long id){
        isYourOffer(id,offerId);
        offerRepository.deleteById(offerId);
    }

    public List<Offer> findMyOffer(Long memberId){
        return offerRepository.findByMemberId(memberId);
    }

    public Offer findOffer(Long offerId){
        return findVerifiedOffer(offerId);
    }
    public List<Offer> findOfferByCollectId(Long collectId){
        return offerRepository.findByCollectId(collectId);
    }

    @Transactional(readOnly = true)
    public Offer findVerifiedOffer(long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new BaseException(OfferErrorCode.OFFER_NOT_FOUND));
    }

    public void saveOfferFile(MultipartFile image, Offer offer) throws IOException {
        String url = s3Service.uploadFile(image); // 이미지 s3에 업로드
        File file = new File(image.getOriginalFilename(), url);
        fileService.save(file); // file repository 저장
        offer.setImageUrl(url);
        offer.setFile(file);
    }

    public void isYourOffer(Long id , Long offerId){
        Offer offer = findVerifiedOffer(offerId);
        if(!Objects.equals(id, offer.getMember().getId())){
            throw new BaseException(OfferErrorCode.OFFER_OWNERSHIP);
        }
    }
}
