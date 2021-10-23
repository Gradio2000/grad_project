package com.graduation.project.controller;

import com.graduation.project.model.Role;
import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.service.UserService;
import com.graduation.project.util.AuthUser;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/api/users")
public class UserController {

   private final UserService userService;
   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;

    public UserController(UserService userService, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(UserController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(UserController.class).slash(user.getUserId()).withSelfRel());
                }
            };

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> register(@Valid @RequestBody User user) {
        user.setRoles(EnumSet.of(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        User created = userService.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{id}")
                .buildAndExpand(created.getUserId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(ASSEMBLER.toModel(user));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<User>>>  getAllUsers(){

        List<EntityModel<User>> entityModels = userRepository.findAll().stream()
                .map(ASSEMBLER::toModel)
                .collect(Collectors.toList());

        Link link = linkTo(UserController.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, link), HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> changeUser(@RequestBody User user, @AuthenticationPrincipal AuthUser authUser){
        User oldUser = authUser.getUser();
        if (user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null){
            oldUser.setPassword(user.getPassword());
        }
        if (user.getName() != null){
            oldUser.setName(user.getName());
        }
       return new ResponseEntity<>(ASSEMBLER.toModel(userRepository.save(oldUser)), HttpStatus.OK) ;
    }


    @GetMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> getAuthUser(@AuthenticationPrincipal AuthUser authUser){
        return new ResponseEntity<>(ASSEMBLER.toModel(authUser.getUser()), HttpStatus.OK);
    }



//    https://www.baeldung.com/spring-boot-bean-validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
