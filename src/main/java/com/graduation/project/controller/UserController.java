package com.graduation.project.controller;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.EnumSet;


@RestController
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setRoles(EnumSet.of(Role.USER_ROLE));
        return userService.save(user);
    }
}
