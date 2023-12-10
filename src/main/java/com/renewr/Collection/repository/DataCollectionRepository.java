package com.renewr.Collection.repository;


import com.renewr.Collection.Entity.DataCollection;
import com.renewr.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCollectionRepository extends JpaRepository<DataCollection,Long> {
    List<DataCollection> findByCollectId(Long collectId);
}
