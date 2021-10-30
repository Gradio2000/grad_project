package com.graduation.project.controller;

import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.RestRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
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

    private final RestRepository restRepository;

    public RestaurantController(RestRepository restRepository) {
        this.restRepository = restRepository;
    }

    private static final RepresentationModelAssemblerSupport<Restaurant, EntityModel<Restaurant>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(RestaurantController.class, (Class<EntityModel<Restaurant>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Restaurant> toModel(Restaurant restaurant) {
                    return EntityModel.of(restaurant, linkTo(RestaurantController.class).slash(restaurant.getRestId()).withSelfRel());
                }
            };

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<Restaurant>>> getAllRest(){
        List<EntityModel<Restaurant>> entityModels = restRepository.findAll().stream()
                .map(ASSEMBLER::toModel)
                .collect(Collectors.toList());

        Link link = linkTo(RestaurantController.class).withSelfRel();
        return new ResponseEntity<>(CollectionModel.of(entityModels, link), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Restaurant>> addRest(@RequestBody Restaurant restaurant){
        restRepository.save(restaurant);
        return new ResponseEntity<>(ASSEMBLER.toModel(restaurant), HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EntityModel<Restaurant>> getRestById(@PathVariable Integer id){
        Restaurant restaurant = restRepository.getById(id);
        return new ResponseEntity<>(ASSEMBLER.toModel(restaurant), HttpStatus.OK);
    }
}
