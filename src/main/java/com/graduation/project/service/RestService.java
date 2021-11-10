package com.graduation.project.service;

import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestService {
    private final RestaurantRepository restaurantRepository;

    public RestService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }


    public ResponseEntity<Restaurant> save(Restaurant restaurant) {
        restaurantRepository.save(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
}
