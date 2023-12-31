package com.renewr.collect.repository;

import com.renewr.collect.entity.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectRepository extends JpaRepository<Collect,Long>{
//    member 패키지 제작 이후, memberId를 통한 글 목록 조회 메서드 필요 (본인이 작성한 글 확인)
   List<Collect> findByMemberId(Long memberId);
   @Modifying
   @Query("DELETE FROM Collect c WHERE c.id = :id")
   void deleteCollectById(@Param("id") Long id);

}
