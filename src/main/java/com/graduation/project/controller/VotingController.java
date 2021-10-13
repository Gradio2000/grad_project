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

import java.util.Date;

@RestController
public class VotingController {

    private final VoitRepository voitRepository;

    public VotingController(VoitRepository voitRepository) {
        this.voitRepository = voitRepository;
    }

    @RequestMapping(value = "/api/voits", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public ResponseEntity<Voit> putVoit(@RequestBody Voit voit){
        voit.setDate(new Date());
        voitRepository.save(voit);
        return new ResponseEntity<Voit>(HttpStatus.CREATED);
    }

}
