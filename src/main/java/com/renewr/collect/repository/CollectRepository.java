package com.renewr.collect.repository;

import com.renewr.collect.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectRepository extends JpaRepository<Collect,Long>{
}