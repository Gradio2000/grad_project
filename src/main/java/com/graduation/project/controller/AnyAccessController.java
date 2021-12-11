package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.model.to.UserTO;
import com.graduation.project.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController()
@RequestMapping("/api/users")
@Tag(name = "Anybody access controller")
public class AnyAccessController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AnyAccessController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(AnyAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {

                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(AdminAccessController.class).slash("users/" + user.getUserId()).withSelfRel());
                }
            };

//    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<EntityModel<User>> register(@Valid @RequestBody User user) {
//        user.setRoles(EnumSet.of(Role.USER));
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEmail(user.getEmail().toLowerCase());
//        User created = userService.save(user);
//        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
//                .path("/api/admin/users/{id}")
//                .buildAndExpand(created.getUserId()).toUri();
//        return ResponseEntity.created(uriOfNewResource).body(ASSEMBLER.toModel(user));
//    }


    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> register(@Valid @RequestBody UserTO userTO) {
        userTO.setPassword(passwordEncoder.encode(userTO.getPassword()));
        User createdUser = userService.save(userTO.getUser(userTO));
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/admin/users/{id}")
                .buildAndExpand(createdUser.getUserId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(ASSEMBLER.toModel(createdUser));
    }


    //    https://www.baeldung.com/spring-boot-bean-validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
