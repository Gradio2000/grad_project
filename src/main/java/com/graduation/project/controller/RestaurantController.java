package com.graduation.project.controller;

import com.graduation.project.model.Dish;
import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.DishRepository;
import com.graduation.project.repository.RestaurantRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/admin/restaurants")
@Tag(name = "Restaurant controller", description = "CRUD restaurants")
public class RestaurantController {

    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final PagedResourcesAssembler<Restaurant> pagedResourcesAssembler;

    public RestaurantController(RestaurantRepository restaurantRepository, DishRepository dishRepository, PagedResourcesAssembler<Restaurant> pagedResourcesAssembler) {
        this.restaurantRepository = restaurantRepository;
        this.dishRepository = dishRepository;
        this.pagedResourcesAssembler = pagedResourcesAssembler;
    }

    private static final RepresentationModelAssemblerSupport<Restaurant, EntityModel<Restaurant>> ASSEMBLER_RESTAURANT =
            new RepresentationModelAssemblerSupport<>(RestaurantController.class, (Class<EntityModel<Restaurant>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Restaurant> toModel(Restaurant restaurant) {
                    Link restaurantLink = linkTo(RestaurantController.class).slash(restaurant.getRestId()).withRel("restaurant");
                    Link dishLink = linkTo(RestaurantController.class).slash(restaurant.getRestId()).slash("dishList").withRel("dishList");
                    return EntityModel.of(restaurant, restaurantLink, dishLink);
                }
            };

    private static final RepresentationModelAssemblerSupport<Dish, EntityModel<Dish>> ASSEMBLER_DISH =
            new RepresentationModelAssemblerSupport<>(DishController.class, (Class<EntityModel<Dish>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Dish> toModel(Dish dish) {
                    Link restaurantLink = linkTo(RestaurantController.class).slash(dish.getRestId()).withRel("restaurant");
                    return EntityModel.of(dish, restaurantLink);
                }
            };

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Restaurant>> addRest(@RequestBody Restaurant restaurant){
        restaurantRepository.save(restaurant);
        return new ResponseEntity<>(ASSEMBLER_RESTAURANT.toModel(restaurant), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Restaurant>> getRestById(@PathVariable Integer id){
        Restaurant restaurant = restaurantRepository.getById(id);
        return new ResponseEntity<>(ASSEMBLER_RESTAURANT.toModel(restaurant), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}/dishList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<EntityModel<Dish>>> getAllDish(@PathVariable Integer id){

        List<EntityModel<Dish>> entityModels = dishRepository.findAllByRestId(id).stream()
                .map(ASSEMBLER_DISH::toModel)
                .collect(Collectors.toList());

        Link restaurants = linkTo(RestaurantController.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, restaurants), HttpStatus.OK);
    }



    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PagedModel<EntityModel<Restaurant>>> getAllRest(
            @RequestParam (defaultValue = "0") Integer page,
            @RequestParam (defaultValue = "20") Integer size){

        Page<Restaurant> restaurantPage = restaurantRepository.findAll(PageRequest.of(page, size));
        PagedModel<EntityModel<Restaurant>> pagedModel =
                pagedResourcesAssembler.toModel(restaurantPage, ASSEMBLER_RESTAURANT);

        return new ResponseEntity<>(pagedModel, HttpStatus.OK);
    }
}