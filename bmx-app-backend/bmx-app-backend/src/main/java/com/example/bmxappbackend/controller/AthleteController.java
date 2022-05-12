package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.model.Athlete;
import com.example.bmxappbackend.model.TrackTimeRecord;
import com.example.bmxappbackend.repository.AthleteRepository;
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
@RequestMapping("/Athlete")
public class AthleteController {

    private final AthleteRepository AthleteRepository;

    public AthleteController(AthleteRepository AthleteRepository) {
        this.AthleteRepository = AthleteRepository;
    }

    /**
     * @return an iterable list of rolls
     */
    @GetMapping
    public @ResponseBody
    Iterable<Athlete> getAll(){
        return AthleteRepository.findAll();
    }
    /**
     * @param id
     * @return a roll by its ID
     */
    @GetMapping(path = "/{id}")
    public Athlete getAthleteController(@PathVariable int id){
        return AthleteRepository.findById(id);
    }

    /**
     * @param Athlete
     * @return a response status whether the operation to add a roll was succesfull
     */
    @PostMapping
    public ResponseEntity<Athlete> createAthleteController(@RequestBody Athlete Athlete){
        Athlete toBeInsertedAthlete= AthleteRepository.save(Athlete);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toBeInsertedAthlete.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * @param id
     * @return a response status whether the delete operation was succesfull
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Athlete> deleteRoll(@PathVariable int id){
        Athlete toBeDeletedAthlete = AthleteRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedAthlete);
    }
}

