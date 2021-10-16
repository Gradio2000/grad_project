package com.graduation.project.controller;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.EnumSet;
import java.util.List;


@RestController
public class UserController {

   private final UserService userService;
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @PostMapping(value = "/api/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        user.setRoles(EnumSet.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users")
                .build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(user);
    }

    @GetMapping(value = "/api/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>>  getAllUsers(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAuthUser(@AuthenticationPrincipal Object authUser){
        return authUser;
    }
}
