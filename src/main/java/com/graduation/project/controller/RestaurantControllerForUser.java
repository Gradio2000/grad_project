package com.graduation.project.controller;

import com.graduation.project.model.Dish;
import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.RestaurantRepository;
import com.graduation.project.util.AssemblerSupport;
import com.graduation.project.util.AuthUser;
import com.graduation.project.util.Util;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/api/user/restaurants")
@Tag(name = "Restaurant controller for user access", description = "View restaurants")
public class RestaurantControllerForUser {
    final Logger logger = LoggerFactory.getLogger(RestaurantControllerForAdmin.class);

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final PagedResourcesAssembler<Restaurant> pagedResourcesAssembler;

    public RestaurantControllerForUser(RestaurantRepository restaurantRepository, DishRepository dishRepository,
                                       PagedResourcesAssembler<Restaurant> pagedResourcesAssembler) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }



    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Restaurant>> getRestById(@PathVariable Integer id, @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into getRestById");

        //check restaurant in DB
        Util.checkRestaurantExist(id);

        Restaurant restaurant = restaurantRepository.getById(id);
        return new ResponseEntity<>(AssemblerSupport.ASSEMBLER_RESTAURANT.toModel(restaurant), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/dishList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Dish>>> getAllDishByRestId(@PathVariable Integer id, @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into getAllDishByRestId");

        //check restaurant in DB
        Util.checkRestaurantExist(id);

        List<EntityModel<Dish>> entityModels = dishRepository.findAllByRestId(id).stream()
                .map(AssemblerSupport.ASSEMBLER_DISH::toModel)
                .collect(Collectors.toList());

        Link restaurants = linkTo(RestaurantControllerForUser.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, restaurants), HttpStatus.OK);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @Cacheable("rest")
    public ResponseEntity<PagedModel<EntityModel<Restaurant>>> getAllRest(@AuthenticationPrincipal AuthUser authUser,
                                                                          @RequestParam(defaultValue = "0") Integer page,
                                                                          @RequestParam (defaultValue = "20") Integer size){
        logger.info(authUser.getUser().getName() + " enter into getAllRest");

        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, size));
        PagedModel<EntityModel<Restaurant>> pagedModel =
                pagedResourcesAssembler.toModel(restaurantPage, AssemblerSupport.ASSEMBLER_RESTAURANT);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }
}
