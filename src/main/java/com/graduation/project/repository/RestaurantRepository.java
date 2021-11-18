package com.graduation.project.repository;

import com.graduation.project.model.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(exported = false)
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    @Query(nativeQuery = true, value = "SELECT * FROM RESTAURANT WHERE REST_ID = :id")
    Restaurant getById(Integer id);

    @Override
    Page<Restaurant> findAll(Pageable pageable);
}
