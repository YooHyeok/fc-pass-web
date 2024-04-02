package com.fc.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassRepository extends JpaRepository<PassEntity, Integer> {

    /**
     * 회원아이디가 UserId인 회원의 이용권과 패키지를 조회한다.
     * 종료일시가 null인것은 기한이 없다는 의미이다.
     * 따라서 정렬 조건으로 들어간 종료일시가 null인 데이터를 최상단으로 올린다.
     * @param userId
     * @return
     */
    @Query("""
        SELECT p FROM PassEntity p 
        JOIN FETCH p.packageEntity 
        WHERE p.userId = :userId
        ORDER BY p.endedAt desc nulls first 
        """)
    List<PassEntity> findByUserId(String userId);
}
