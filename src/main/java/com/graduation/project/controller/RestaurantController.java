package com.graduation.project.controller;


import com.graduation.project.model.Restaurant;
import com.graduation.project.service.RestService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/restaurants")
class RestaurantController {
    private final RestService restService;

    public RestaurantController(RestService restService) {
        this.restService = restService;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> register(@RequestBody Restaurant restaurant) {
        return restService.save(restaurant);
    }
}
