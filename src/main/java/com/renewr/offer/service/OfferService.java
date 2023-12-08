package com.renewr.offer.service;

import com.renewr.Collection.Entity.DataCollection;
import com.renewr.Collection.repository.DataCollectionRepository;
import com.renewr.collect.entity.Collect;
import com.renewr.collect.repository.CollectRepository;
import com.renewr.collect.service.CollectService;
import com.renewr.global.common.BaseException;
import com.renewr.global.exception.GlobalErrorCode;
import com.renewr.offer.entity.Offer;
import com.renewr.offer.repository.OfferRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfferService {
    private final OfferRepository offerRepository;
    private final CollectService collectService;
    private final DataCollectionRepository dataCollectionRepository;

    public Offer saveOffer(Offer offer,Long collectId) {
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

}
