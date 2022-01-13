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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin/restaurants")
@Tag(name = "Restaurant controller for admin access", description = "CRUD restaurants")
public class RestaurantControllerForAdmin {
    final Logger logger = LoggerFactory.getLogger(RestaurantControllerForAdmin.class);

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;

    public RestaurantControllerForAdmin(RestaurantRepository restaurantRepository, DishRepository dishRepository) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "rest", allEntries = true)
    public ResponseEntity<EntityModel<Restaurant>> addRest(@RequestBody Restaurant restaurant,
                                                           @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into addRest");

        restaurantRepository.save(restaurant);
        return new ResponseEntity<>(AssemblerSupport.ASSEMBLER_RESTAURANT.toModel(restaurant), HttpStatus.CREATED);
    }


    @PostMapping(value = "/{id}/dishList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Dish>>> addDishListByRestId(@PathVariable Integer id,
                                                                                  @RequestBody List<Dish> dishList,
                                                                                  @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into addDishListByRestId");

        //check restaurant in DB
        Util.checkRestaurantExist(id);

        dishList.forEach(dish1 -> {
            dish1.setRestId(id);
            dish1.setLocalDate(LocalDate.now());
        });

        List<EntityModel<Dish>> entityModels = dishRepository.saveAll(dishList).stream()
                .map(AssemblerSupport.ASSEMBLER_DISH::toModel)
                .collect(Collectors.toList());

        return new ResponseEntity<>(CollectionModel.of(entityModels), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "rest", allEntries = true)
    public HttpStatus deleteRest(@PathVariable Integer id, @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into deleteRest");

        //check restaurant in DB
        Util.checkRestaurantExist(id);

        restaurantRepository.deleteById(id);
        return HttpStatus.OK;
    }


    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CacheEvict(value = "rest", allEntries = true)
    public ResponseEntity<EntityModel<Restaurant>> patchRestById(@PathVariable Integer id,
                                                                 @RequestBody Restaurant newRestaurant,
                                                                 @AuthenticationPrincipal AuthUser authUser){
        logger.info(authUser.getUser().getName() + " enter into patchRestById");

        //check restaurant in DB
        Util.checkRestaurantExist(id);

        Restaurant oldRestaurant = restaurantRepository.getById(id);
        oldRestaurant.setName(newRestaurant.getName());
        restaurantRepository.save(oldRestaurant);
        return new ResponseEntity<>(AssemblerSupport.ASSEMBLER_RESTAURANT.toModel(oldRestaurant), HttpStatus.OK);
    }

}
