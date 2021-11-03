package com.graduation.project.repository;

import com.graduation.project.model.Restaurant;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


@Tag(name = "Restaurant controller", description = "CRUD restaurants")
public interface RestRepository extends JpaRepository<Restaurant, Integer> {

}
