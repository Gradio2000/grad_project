package com.graduation.project.repository;

import com.graduation.project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RestResource(exported = false)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.name = :name")
    Optional<User> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT * FROM USERS WHERE USER_ID = :id")
    @Override
    User getById(Integer id);
}
