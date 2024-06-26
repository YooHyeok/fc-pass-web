package com.fc.pass.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity, Integer> {
    List<UserGroupMappingEntity> findDistinctUserGroupIdByOrderByUserGroupId(); // findDistinctByOrderByUserGroupId도 가능
}