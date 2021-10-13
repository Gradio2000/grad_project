package com.graduation.project.controller;

import com.graduation.project.model.Voit;
import com.graduation.project.repository.VoitRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


public class VotingController {

    private final VoitRepository voitRepository;

    public VotingController(VoitRepository voitRepository) {
        this.voitRepository = voitRepository;
    }


    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    public void putVoit(@RequestBody Voit voit){
        System.out.println("qqqqq");
        voit.setDate(new Date());
        voitRepository.save(voit);
    }

}
