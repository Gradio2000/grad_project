package com.graduation.project.controller;

import com.graduation.project.model.User;
import com.graduation.project.model.Vote;
import com.graduation.project.model.to.UserToWithoutPassword;
import com.graduation.project.model.to.VoteTO;
import com.graduation.project.repository.UserRepository;
import com.graduation.project.repository.VoteRepository;
import com.graduation.project.service.VoteService;
import com.graduation.project.util.AuthUser;
import com.graduation.project.util.VoteException;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/api/user")
@Tag(name = "User access controller")
public class UserAccessController {
    final Logger logger = LoggerFactory.getLogger(UserAccessController.class);

    private final UserRepository userRepository;
    private final VoteService voteService;
    private final VoteRepository voteRepository;

    public UserAccessController(UserRepository userRepository, VoteService voteService, VoteRepository voteRepository) {
        this.userRepository = userRepository;
        this.voteService = voteService;
        this.voteRepository = voteRepository;
    }

    private static final RepresentationModelAssemblerSupport<User, EntityModel<User>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(UserAccessController.class, (Class<EntityModel<User>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<User> toModel(User user) {
                    return EntityModel.of(user, linkTo(UserAccessController.class).slash(user.getUserId()).withSelfRel());
                }
            };


    @PutMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> changeUser(@RequestBody UserToWithoutPassword userTO,
                                                        @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into changeUser");

        User oldUser = authUser.getUser();
        if (userTO.getEmail() != null){
            oldUser.setEmail(userTO.getEmail());
        }

        if (userTO.getName() != null){
            oldUser.setName(userTO.getName());
        }
       return new ResponseEntity<>(ASSEMBLER.toModel(userRepository.save(oldUser)), HttpStatus.OK) ;
    }


    @GetMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<User>> getAuthUser(@AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into getAuthUser");

        return new ResponseEntity<>(ASSEMBLER.toModel(authUser.getUser()), HttpStatus.OK);
    }


    @CacheEvict("voits")
    @PutMapping(value = "/voits", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> changeVote(@RequestBody Vote vote,
                                           @AuthenticationPrincipal AuthUser authUser) throws VoteException {

        logger.info(authUser.getUser().getName() + " enter into changeVote");

        Vote voteFromDB = voteRepository.getByUserIdAndDate(authUser.getUser().getUserId(), LocalDate.now());

        if (voteFromDB != null){
            voteFromDB.setRestId(vote.getRestId());
            return voteService.addVote(voteFromDB, authUser);
        }
        else {
            vote.setUserId(authUser.getUser().getUserId());
            vote.setLocalDate(LocalDate.now());
            vote.setLocalTime(LocalTime.now());
            try {
                return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
            } catch (Exception e) {
                throw new VoteException();
            }
        }

    }


    @Cacheable("voits")
    @GetMapping(value = "/voits", produces = MediaType.APPLICATION_JSON_VALUE)
    public CollectionModel<VoteTO> getAllVotes(@AuthenticationPrincipal AuthUser authUser,
                                                          @RequestParam (defaultValue = "0") Integer page,
                                                          @RequestParam (defaultValue = "20") Integer size){

        logger.info(authUser.getUser().getName() + " enter into getAllVotes");

        Page<Vote> votePage = voteRepository.findAllByUserId(PageRequest.of(page, size), authUser.getUser().getUserId());
        List<VoteTO> voteTOList = votePage.stream()
                .map(vote -> new VoteTO(vote.getRestId(), vote.getLocalDate(), vote.getLocalTime()))
                .collect(Collectors.toList());

        return CollectionModel.of(voteTOList);
    }


    @ExceptionHandler(VoteException.class)
    public ResponseEntity<Map<String, String>> votingException(VoteException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getMessage());
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
