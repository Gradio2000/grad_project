package com.graduation.project.util;

import com.graduation.project.controller.RestaurantControllerForUser;
import com.graduation.project.model.Dish;
import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.DishRepository;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class AssemblerSupport {

    public static final RepresentationModelAssemblerSupport<Restaurant, EntityModel<Restaurant>> ASSEMBLER_RESTAURANT =
            new RepresentationModelAssemblerSupport<>(RestaurantControllerForUser.class, (Class<EntityModel<Restaurant>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Restaurant> toModel(Restaurant restaurant) {
                    Link restaurantLink = linkTo(RestaurantControllerForUser.class).slash(restaurant.getRestId()).withRel("restaurant");
                    Link dishLink = linkTo(RestaurantControllerForUser.class).slash(restaurant.getRestId()).slash("dishList").withRel("dishList");
                    return EntityModel.of(restaurant, restaurantLink, dishLink);
                }
            };

    public static final RepresentationModelAssemblerSupport<Dish, EntityModel<Dish>> ASSEMBLER_DISH =
            new RepresentationModelAssemblerSupport<>(DishRepository.class, (Class<EntityModel<Dish>>) (Class<?>) EntityModel.class) {
                @Override
                public EntityModel<Dish> toModel(Dish dish) {
                    Link restaurantLink = linkTo(RestaurantControllerForUser.class).slash(dish.getRestId()).withRel("restaurant");
                    return EntityModel.of(dish, restaurantLink);
                }
            };
}
