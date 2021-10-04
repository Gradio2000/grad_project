package com.graduation.project.repository;

import com.graduation.project.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestRepository extends JpaRepository<Restaurant, Integer> {
}
