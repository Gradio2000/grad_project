package com.graduation.project.controller;

import com.graduation.project.dto.UserDto;
import com.graduation.project.model.User;
import com.graduation.project.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/users")
public class UserController {

   private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        return userService.getUserByid(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@RequestBody User user) {
        return userService.save(user);
    }
}
