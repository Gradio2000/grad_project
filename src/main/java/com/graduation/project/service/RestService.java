package com.graduation.project.service;

import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.RestRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class RestService {
    private final RestRepository restRepository;

    public RestService(RestRepository restRepository) {
        this.restRepository = restRepository;
    }


    public ResponseEntity<Restaurant> save(Restaurant restaurant) {
        restRepository.save(restaurant);
        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }
}
