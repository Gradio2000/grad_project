package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.model.Vote;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoteRepository;
import com.graduation.project.service.VoteService;
import com.graduation.project.util.AuthUser;
import com.graduation.project.util.VoteException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User access controller")
public class UserAccessController {
    final Logger logger = LoggerFactory.getLogger(UserAccessController.class);
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VoteRepository voteRepository;
    private final VoteService voteService;


    public UserAccessController(PasswordEncoder passwordEncoder, UserRepository userRepository, VoteRepository voteRepository, VoteService voteService) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.voteRepository = voteRepository;
        this.voteService = voteService;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(UserAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(UserAccessController.class).slash(user.getUserId()).withSelfRel());
                }
            };


    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> changeUser(@RequestBody User user,
                                                        @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into changeUser");

        User oldUser = authUser.getUser();
        if (user.getEmail() != null){
            oldUser.setEmail(user.getEmail());
        }
        if (user.getPassword() != null){
            oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        if (user.getName() != null){
            oldUser.setName(user.getName());
        }
       return new ResponseEntity<>(ASSEMBLER.toModel(userRepository.save(oldUser)), HttpStatus.OK) ;
    }


    @GetMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> getAuthUser(@AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into getAuthUser");

        return new ResponseEntity<>(ASSEMBLER.toModel(authUser.getUser()), HttpStatus.OK);
    }

    @PostMapping(value = "/voits", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> putVote(@RequestBody Vote vote, @AuthenticationPrincipal AuthUser authUser) throws VoteException {
        logger.info(authUser.getUser().getName() + " enter into putVote");
        return voteService.addVoit(vote, authUser);
    }



    @ExceptionHandler(VoteException.class)
    public ResponseEntity<Map<String, String>> votingException(VoteException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
