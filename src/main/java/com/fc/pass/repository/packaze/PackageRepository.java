package com.fc.pass.repository.packaze;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PackageRepository extends JpaRepository<PackageEntity, Integer> {
    /**
     * Package 이름을 기준으로 정렬한 Pacakge 전체 조회
     * @return PackageEntity타입의 리스트를 반환
     */
    List<PackageEntity> findAllByOrderByPackageName();
}
