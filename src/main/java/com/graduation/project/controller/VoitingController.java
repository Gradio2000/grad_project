package com.graduation.project.controller;

import com.graduation.project.model.Restaurant;
import com.graduation.project.repository.RestRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
public class VoitingController {

    private final RestRepository repository;

    public VoitingController(RestRepository repository) {
        this.repository = repository;
    }


    @GetMapping(value = "start")
    public List<Restaurant> getAllAlbums() {
        return repository.findAll();
    }

}
