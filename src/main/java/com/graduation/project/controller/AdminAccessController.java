package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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


    private final UserRepository userRepository;
    private final DishRepository dishRepository;


    public AdminAccessController(UserRepository userRepository, VoitRepository voitRepository,
                                DishRepository dishRepository) {
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(AdminAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(AdminAccessController.class).slash("users").slash(user.getUserId()).withSelfRel());
                }
            };

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<User>>> getAllUsers(){

        List<EntityModel<User>> entityModels = userRepository.findAll().stream()
                .map(ASSEMBLER::toModel)
                .collect(Collectors.toList());

        Link link = linkTo(UserAccessController.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, link), HttpStatus.OK);
    }





    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> getUserById(@PathVariable Integer id){
        return new ResponseEntity<>(ASSEMBLER.toModel(userRepository.getById(id)), HttpStatus.OK);
    }




}
