package com.graduation.project.controller;

import com.graduation.project.model.Dish;
import com.graduation.project.repository.DishRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/admin/dishes")
@Tag(name = "Dish controller", description = "CRUD dish")
public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    private static final RepresentationModelAssemblerSupport<Dish, EntityModel<Dish>> ASSEMBLER =
            new RepresentationModelAssemblerSupport<>(DishController.class, (Class<EntityModel<Dish>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Dish> toModel(Dish dish) {
                    Link restaurant = linkTo(DishController.class).slash(dish.getRestId()).withRel("restaurant");
                    return EntityModel.of(dish, restaurant);
                }
            };

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish){
        dish.setLocalDate(LocalDate.now());
        dishRepository.save(dish);
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }



    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(JdbcSQLIntegrityConstraintViolationException.class)
    public Map<String, String> handleExceptions(JdbcSQLIntegrityConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", "Restaurant is not present");
        return errors;
    }

}
