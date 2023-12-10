package com.renewr.offer.service;

import com.renewr.Collection.Entity.DataCollection;
import com.renewr.Collection.repository.DataCollectionRepository;
import com.renewr.S3.S3Service;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.repository.CollectRepository;
import com.renewr.collect.service.CollectService;
import com.renewr.file.entity.File;
import com.renewr.file.service.FileService;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import com.renewr.offer.entity.Offer;
import com.renewr.offer.repository.OfferRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    private final OfferRepository offerRepository;
    private final CollectService collectService;
    private final DataCollectionRepository dataCollectionRepository;
    private final FileService fileService;
    private final S3Service s3Service;

    public Offer saveOffer(Offer offer,Long collectId,MultipartFile image) throws IOException {
        Collect collect = collectService.findVerifiedCollect(collectId);
        if(collect.getStatus() == Collect.CollectStatus.CLOSED){
            throw new RuntimeException();
        }

        Offer newOffer = Offer.builder()
                .content(offer.getContent())
                .imageUrl(offer.getImageUrl())
                .location(offer.getLocation())
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

    public void deleteOffer(Long offerId){
        offerRepository.deleteById(offerId);
    }

//    public List<Offer> findMyOffer(Long memberId){
//        return offerRepository.findByMemberId(memberId);
//    }

    public Offer findOffer(Long offerId){
        return findVerifiedOffer(offerId);
    }
    public List<Offer> findOfferByCollectId(Long collectId){
        return offerRepository.findByCollectId(collectId);
    }

    @Transactional(readOnly = true)
    public Offer findVerifiedOffer(long offerId) {
        return offerRepository.findById(offerId)
                .orElseThrow(() -> new BaseException(GlobalErrorCode.NOT_FOUND));
    }

    public void saveOfferFile(MultipartFile image, Offer offer) throws IOException {
        String url = s3Service.uploadFile(image); // 이미지 s3에 업로드
        File file = new File(image.getOriginalFilename(), url);
        fileService.save(file); // file repository 저장
        offer.setImageUrl(url);
        offer.setFile(file);
    }

}
