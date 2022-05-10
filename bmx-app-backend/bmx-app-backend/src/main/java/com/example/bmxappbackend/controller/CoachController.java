package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.model.Coach;
import com.example.bmxappbackend.model.TrackTimeRecord;
import com.example.bmxappbackend.repository.CoachRepository;
import com.example.bmxappbackend.repository.TrackTimeRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Author: Mortada M'Rabet & Sem H
 * @Description: This class manages every mapping, which allows for data manipulation
 */

@RestController
@RequestMapping("/CoachController")
public class CoachController {

    private final CoachRepository coachRepository;

    public CoachController(CoachRepository coachRepository) {
        this.coachRepository = coachRepository;
    }

    /**
     * @return an iterable list of rolls
     */
    @GetMapping
    public @ResponseBody
    Iterable<Coach> getAll(){
        return coachRepository.findAll();
    }
    /**
     * @param id
     * @return a roll by its ID
     */
    @GetMapping(path = "/{id}")
    public Coach getCoachController(@PathVariable int id){
        return coachRepository.findById(id);
    }

    /**
     * @param coach
     * @return a response status whether the operation to add a roll was succesfull
     */
    @PostMapping
    public ResponseEntity<Coach> createCoachcontroller(@RequestBody Coach coach){
        Coach toBeinsertedCoach= coachRepository.save(coach);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toBeinsertedCoach.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * @param id
     * @return a response status whether the delete operation was succesfull
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Coach> deleteRoll(@PathVariable int id){
        Coach toBeDeletedCoach = coachRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedCoach);
    }
}
