package com.graduation.project.repository;

import com.graduation.project.model.Voit;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Tag(name = "VoitRepository")
public interface VoitRepository extends JpaRepository<Voit, Integer> {

    @Query("select v from Voit v where v.userId = :userId")
    Voit findByUserId(Integer userId);

    @Query("select v from Voit v where v.userId = :userId and v.localDateTime between :start and :finish")
    Voit findByLocalDate(LocalDateTime start, LocalDateTime finish, int userId);

    @Modifying
    @Query("update Voit v set v.localDateTime = :localDateTime, v.restId = :restId where v.voit_id = :voitId")
    @Transactional
    void update(int voitId, LocalDateTime localDateTime, int restId);
}
