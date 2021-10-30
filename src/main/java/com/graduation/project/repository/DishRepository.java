package com.graduation.project.repository;

import com.graduation.project.model.Dish;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

@Tag(name = "Dish controller")
public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Override
//    @RestResource(exported = false)
    <S extends Dish> S save(S entity);
}
