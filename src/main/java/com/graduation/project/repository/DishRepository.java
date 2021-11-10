package com.graduation.project.repository;

import com.graduation.project.model.Dish;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Integer> {
    @Override
    @RestResource(exported = false)
    <S extends Dish> S save(S entity);

    @Override
    @RestResource(exported = false)
    Optional<Dish> findById(Integer integer);

    @Override
    @RestResource(exported = false)
    void deleteById(Integer integer);

    @Override
    @RestResource(exported = false)
    List<Dish> findAll();

    @Override
    @RestResource(exported = false)
    <S extends Dish> List<S> findAll(Example<S> example);

    @Override
    @RestResource(exported = false)
    <S extends Dish> List<S> findAll(Example<S> example, Sort sort);

    @Override
    @RestResource(exported = false)
    Page<Dish> findAll(Pageable pageable);

    @Override
    @RestResource(exported = false)
    <S extends Dish> Page<S> findAll(Example<S> example, Pageable pageable);

    @RestResource(exported = false)
    List<Dish>findAllByRestId(Integer id);
}


