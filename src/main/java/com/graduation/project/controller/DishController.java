package com.graduation.project.controller;

import com.graduation.project.model.Dish;
import com.graduation.project.repository.DishRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class DishController {

    private final DishRepository dishRepository;

    public DishController(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @PostMapping(value = "api/dishes", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> saveDish(@RequestBody Dish dish){
        dish.setLocalDateTime(LocalDateTime.now());
        dishRepository.save(dish);
        return new ResponseEntity<>(dish, HttpStatus.CREATED);
    }
}
