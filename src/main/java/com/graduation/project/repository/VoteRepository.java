package com.graduation.project.repository;

import com.graduation.project.model.Vote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    Page<Vote> findAllByUserId(Pageable pageable, int id);

    List<Vote> findAllByUserId(int userId);


    @Query(nativeQuery = true, value = "SELECT * FROM VOIT WHERE USER_ID = :id and DATE = :date")
    Vote getByUserIdAndDate(Integer id, LocalDate date);

}
