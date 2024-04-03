package com.fc.pass.repository.pass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Integer> {
    List<BulkPassEntity> findAllByOrderByStartedAtDesc();
}
