package com.renewr.collection.repository;


import com.renewr.collection.Entity.DataCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DataCollectionRepository extends JpaRepository<DataCollection,Long> {
    List<DataCollection> findByCollectId(Long collectId);
    void deleteByOfferId(Long offerId);
}
