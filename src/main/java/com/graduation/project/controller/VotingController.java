package com.graduation.project.controller;

import com.graduation.project.model.Restaurant;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.service.VoitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@RestController
@Tag(name = "VotingController")
@RequestMapping("/api")
public class VotingController {

    private final VoitRepository voitRepository;
    private final VoitService voitService;

    public VotingController(VoitRepository voitRepository, VoitService voitService) {
        this.voitRepository = voitRepository;
        this.voitService = voitService;
    }

    @PostMapping(value = "voits", consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Voit> putVoit(@RequestBody Voit voit){
        LocalDateTime dateStart = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0));
        LocalDateTime dateFinish = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59));
        LocalTime localTime = LocalTime.of(11, 0);
        Voit voit1 = voitRepository.findByLocalDate(dateStart, dateFinish, voit.getUserId());
        if (voit1 == null){
            voit.setLocalDateTime(LocalDateTime.now());
            voitRepository.save(voit);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        if (voit1.getLocalDateTime().toLocalTime().isBefore(localTime)){
            LocalDateTime localDateTime = LocalDateTime.now();
            int voitId = voit1.getVoit_id();
            int restId = voit.getRestId();
            voitRepository.update(voitId, localDateTime, restId);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }

    @GetMapping(value = "/voits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Voit> getVoitLst(){
       return voitRepository.findAll();
    }

    @GetMapping("/result")
    public List<Restaurant> getResult(){
        return voitService.getResult();
    }
}
