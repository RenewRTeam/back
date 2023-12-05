package com.renewr.requirements.repository;

import com.renewr.requirements.entity.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement,Long> {
}
