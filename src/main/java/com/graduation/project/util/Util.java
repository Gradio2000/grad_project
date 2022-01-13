package com.graduation.project.util;

import com.graduation.project.repository.RestaurantRepository;
import org.springframework.stereotype.Component;

@Component
public class Util {

    private static RestaurantRepository restaurantRepository;

    public Util(RestaurantRepository restaurantRepository) {
        Util.restaurantRepository = restaurantRepository;
    }

    public static void checkRestaurantExist(int id){
        if (restaurantRepository.findById(id).isEmpty()){
            throw new IllegalRequestDataException("Restaurant is not found on DB");
        }
    }



}
