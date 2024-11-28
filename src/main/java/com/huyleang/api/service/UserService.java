package com.huyleang.api.service;

import com.huyleang.api.entities.UserEntity;
import com.huyleang.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Value("${db.limit-size}")
    private long limitSize;

    public boolean isDatabaseSizeWithinLimit() {
        Long databaseSize = userRepository.getDatabaseSize();
        return databaseSize != null && databaseSize <= limitSize;
    }

    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    public UserEntity saveUser(UserEntity user) throws Exception {
        if (!isDatabaseSizeWithinLimit()) {
            throw new Exception("Database size limit exceeded");
        }
        if (user != null) {
            return userRepository.save(user);
        }
        return null;
    }

}
