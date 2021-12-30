package com.graduation.project.repository;

import com.graduation.project.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDate;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

//    @Query("select v from Vote v where v.userId = :userId and v.localDateTime between :start and :finish")
//    Vote findByLocalDate(LocalDateTime start, LocalDateTime finish, int userId);

//    @Modifying
//    @Query("update Vote v set v.localDateTime = :localDateTime, v.restId = :restId where v.voitId = :voitId")
//    @Transactional
//    void update(int voitId, LocalDateTime localDateTime, int restId);

    List<Vote> findAllByUserId(int userId);


    @Query(nativeQuery = true, value = "SELECT * FROM VOIT WHERE USER_ID = :id and DATE = :date")
    Vote getByUserIdAndDate(Integer id, LocalDate date);

}
