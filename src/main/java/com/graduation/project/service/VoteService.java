package com.graduation.project.service;

import com.graduation.project.model.Vote;
import com.graduation.project.repository.RestaurantRepository;
import com.graduation.project.repository.VoteRepository;
import com.graduation.project.util.IllegalRequestDataException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final RestaurantRepository restaurantRepository;

    public VoteService(VoteRepository voteRepository, RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
    }

    //insert vote into db
    public ResponseEntity<Vote> addVote(Vote vote)  {
        // check exist restaurant in DB
        if (!restaurantRepository.existsById(vote.getRestId())){
            throw new IllegalRequestDataException("Restaurant is not found on DB");
        }


        LocalTime time = LocalTime.of(11, 0);
        if (vote.getLocalTime().isBefore(time)){
            vote.setLocalDate(LocalDate.now());
            vote.setLocalTime(LocalTime.now());
            return new ResponseEntity<>(voteRepository.save(vote), HttpStatus.OK);
        }
        else throw new IllegalRequestDataException("Vote is already exist! Come tomorrow!");
    }
}

