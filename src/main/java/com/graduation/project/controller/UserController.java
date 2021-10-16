package com.graduation.project.controller;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.EnumSet;


@RestController
public class UserController {

   private final UserService userService;
   private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setRoles(EnumSet.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.save(user);
    }

    @GetMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAuthUser(@AuthenticationPrincipal Object authUser){
        return authUser;
    }

}
