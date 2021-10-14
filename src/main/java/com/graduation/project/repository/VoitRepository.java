package com.graduation.project.repository;

import com.graduation.project.model.Voit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface VoitRepository extends JpaRepository<Voit, Integer> {

    @Query("select v from Voit v where v.userId = :userId")
    Voit findByUserId(Integer userId);

    @Query("select v from Voit v where v.userId = :userId and v.localDateTime between :start and :finish")
    Voit findByLocalDate(LocalDateTime start, LocalDateTime finish, int userId);

}
