package com.graduation.project.service;

import com.graduation.project.model.Vote;
import com.graduation.project.repository.VoteRepository;
import com.graduation.project.util.AuthUser;
import com.graduation.project.util.VoteException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class VoteService {
    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    //insert vote into db
    public ResponseEntity<Vote> addVoit(Vote vote, AuthUser authUser) throws VoteException {
        //проверяем, голосовал ли пользователь сегодня?
        LocalDateTime dateStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime dateFinish = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        LocalTime localTime = LocalTime.of(11, 0);

        Vote voteFromDB = voteRepository.findByLocalDate(dateStart, dateFinish, authUser.getUser().getUserId());

        //если не голосовал, то...
        if (voteFromDB == null){
            vote.setUserId(authUser.getUser().getUserId());
            vote.setLocalDateTime(LocalDateTime.now());
            voteRepository.save(vote);
            return new ResponseEntity<>(vote, HttpStatus.CREATED);
        }

        //если уже сегодня проголосовал до 11.00, то изменяем голос
        if (voteFromDB.getLocalDateTime().toLocalTime().isBefore(localTime)){
            LocalDateTime localDateTime = LocalDateTime.now();
            int voitId = voteFromDB.getVoitId();
            int restId = vote.getRestId();
            voteRepository.update(voitId, localDateTime, restId);
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }

        throw new VoteException();
//        return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }
}

