package com.graduation.project.service;

import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
       return userRepository.save(user);
    }
}
