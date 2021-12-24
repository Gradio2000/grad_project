package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.util.AuthUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin access controller")
public class AdminAccessController {
    final Logger logger = LoggerFactory.getLogger(AdminAccessController.class);
    private final UserRepository userRepository;

    public AdminAccessController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER_USER =
            new RepresentationModelAssemblerSupport<>(AdminAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(AdminAccessController.class).slash("users").slash(user.getUserId()).withSelfRel());
                }
            };

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers(@AuthenticationPrincipal AuthUser authUser){
        if (authUser != null) {
            logger.info(authUser.getUser().getName() + " enter into getAllUsers");
        }

        List<EntityModel<User>> entityModels = userRepository.findAll().stream()
                .map(ASSEMBLER_USER::toModel)
                .collect(Collectors.toList());

        Link link = linkTo(UserAccessController.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, link), HttpStatus.OK);
    }


    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Integer id,
                                                         @AuthenticationPrincipal AuthUser authUser){
        if (authUser != null) {
            logger.info(authUser.getUser().getName() + " enter into getUserById");
        }

        return new ResponseEntity<>(ASSEMBLER_USER.toModel(userRepository.getById(id)), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, String>> restaurantPatchingException() {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "User is not found on DB");
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


}
