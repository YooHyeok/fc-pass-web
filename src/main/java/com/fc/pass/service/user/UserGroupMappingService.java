package com.fc.pass.service.user;

import com.fc.pass.repository.user.UserGroupMappingEntity;
import com.fc.pass.repository.user.UserGroupMappingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
public class UserGroupMappingService {
    private final UserGroupMappingRepository userGroupMappingRepository;

    public UserGroupMappingService(UserGroupMappingRepository userGroupMappingRepository) {
        this.userGroupMappingRepository = userGroupMappingRepository;
    }

    public List<String> getAllUserGroupId() {
        List<UserGroupMappingEntity> userGroupMappingEntities = userGroupMappingRepository.findDistinctUserGroupIdByOrderByUserGroupId();
        return userGroupMappingEntities.stream().map(UserGroupMappingEntity::getUserGroupId).distinct().toList();
    }
}
