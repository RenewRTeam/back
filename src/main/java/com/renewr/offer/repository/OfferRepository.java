package com.renewr.offer.repository;

import com.renewr.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findByMemberId(Long memberId);
    List<Offer> findByCollectId(Long collectId);
}
