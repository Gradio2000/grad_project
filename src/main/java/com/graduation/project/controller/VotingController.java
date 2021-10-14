package com.graduation.project.controller;

import com.graduation.project.model.Voit;
import com.graduation.project.repository.VoitRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class VotingController {

    private final VoitRepository voitRepository;

    public VotingController(VoitRepository voitRepository) {
        this.voitRepository = voitRepository;
    }

    @RequestMapping(value = "/api/voits", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Voit> putVoit(@RequestBody Voit voit){
        LocalDateTime dateStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime dateFinish = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        Voit voit1 = voitRepository.findByLocalDate(dateStart, dateFinish, voit.getUserId());
        if (voit1 == null){
            voit.setLocalDateTime(LocalDateTime.now());
            voitRepository.save(voit);
            return new ResponseEntity<Voit>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Voit>(HttpStatus.SEE_OTHER);

    }

}
