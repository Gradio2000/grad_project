package com.graduation.project.repository;

import com.graduation.project.model.Voit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RepositoryRestResource(exported = false)
public interface VoitRepository extends JpaRepository<Voit, Integer> {

    @Query("select v from Voit v where v.userId = :userId and v.localDate between :start and :finish")
    Voit findByLocalDate(LocalDateTime start, LocalDateTime finish, int userId);

    @Modifying
    @Query("update Voit v set v.localDate = :localDateTime, v.restId = :restId where v.voitId = :voitId")
    @Transactional
    void update(int voitId, LocalDateTime localDateTime, int restId);
}
