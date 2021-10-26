package com.graduation.project.controller;

import com.graduation.project.model.Restaurant;
import com.graduation.project.model.User;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.service.VoitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin access controller")
public class AdminAccessController {


    private final UserRepository userRepository;
    private final VoitRepository voitRepository;
    private final VoitService voitService;

    public AdminAccessController(UserRepository userRepository, VoitRepository voitRepository, VoitService voitService) {
        this.userRepository = userRepository;
        this.voitRepository = voitRepository;
        this.voitService = voitService;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(UserAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(UserAccessController.class).slash(user.getUserId()).withSelfRel());
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

    @GetMapping(value = "/voits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Voit> getVoitLst(){
        return voitRepository.findAll();
    }

    @GetMapping("/result")
    public ResponseEntity<List<Restaurant>> getResult(){
        return voitService.getResult();
    }

}
