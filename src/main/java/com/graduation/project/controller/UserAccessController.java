package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.util.AuthUser;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User access controller")
public class UserAccessController {

   private final PasswordEncoder passwordEncoder;
   private final UserRepository userRepository;
   private final VoitRepository voitRepository;

    public UserAccessController(PasswordEncoder passwordEncoder, UserRepository userRepository, VoitRepository voitRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.voitRepository = voitRepository;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(UserAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(UserAccessController.class).slash(user.getUserId()).withSelfRel());
                }
            };


    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> changeUser(@RequestBody User user, @AuthenticationPrincipal AuthUser authUser){
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
        return new ResponseEntity<>(ASSEMBLER.toModel(authUser.getUser()), HttpStatus.OK);
    }

    @PostMapping(value = "/voits", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voit> putVoit(@RequestBody Voit voit, @AuthenticationPrincipal AuthUser authUser){
        voit.setUserId(authUser.getUser().getUserId());
        voit.setLocalDate(LocalDate.now());
        voit.setLocalTime(LocalTime.now());
        voitRepository.save(voit);
        return new ResponseEntity<>(voit, HttpStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public Map<String, String> handleExceptions(JdbcSQLIntegrityConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Vote is already exist");
        return errors;
    }
}
