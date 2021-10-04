package com.graduation.project.service;

import com.graduation.project.dto.UserDto;
import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDto userDto;

    public UserService(UserRepository userRepository, UserDto userDto) {
        this.userRepository = userRepository;
        this.userDto = userDto;
    }

    public ResponseEntity<UserDto> getUserByid(int id){
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        userDto.setName(user.getName());
//        userDto.setRoleList(user.getRoles());
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    public ResponseEntity<User> save(User user) {
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
