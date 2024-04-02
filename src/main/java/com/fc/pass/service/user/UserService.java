package com.fc.pass.service.user;

import com.fc.pass.repository.user.UserEntity;
import com.fc.pass.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        return UserModelMapper.INSTANCE.toUser(userEntity);
    }
}
