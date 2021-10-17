package com.graduation.project.controller;

import com.graduation.project.model.Restaurant;
import com.graduation.project.model.Voit;
import com.graduation.project.repository.VoitRepository;
import com.graduation.project.service.VoitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(value = "/voits", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voit> putVoit(@RequestBody Voit voit){
       return voitService.addVoit(voit);
    }

    @GetMapping(value = "/voits", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Voit> getVoitLst(){
       return voitRepository.findAll();
    }

    @GetMapping("/result")
    public ResponseEntity<List<Restaurant>> getResult(){
        return voitService.getResult();
    }
}
