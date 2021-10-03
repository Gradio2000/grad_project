package com.graduation.project.service;

import com.graduation.project.dto.UserDto;
import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserDto userDto;
    private final RoleService roleService;

    public UserService(UserRepository userRepository, UserDto userDto, RoleService roleService) {
        this.userRepository = userRepository;
        this.userDto = userDto;
        this.roleService = roleService;
    }

    public ResponseEntity<UserDto> getUserByid(int id){
        User user = userRepository.findById(id).orElse(null);
        assert user != null;
        userDto.setName(user.getName());
        userDto.setRoleList(user.getRoles());
        return new ResponseEntity<>(userDto, HttpStatus.FOUND);
    }

    public ResponseEntity<User> save(User user) {
        Role userRole = roleService.getUserRole();
        List<Role> roles = new ArrayList<>();
        roles.add(userRole);
        user.setRoles(roles);
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
