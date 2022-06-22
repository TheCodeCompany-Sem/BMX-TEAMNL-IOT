package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.exceptions.PreConditionFailedException;
import com.example.bmxappbackend.exceptions.ResourceNotFoundException;
import com.example.bmxappbackend.model.Athlete;
import com.example.bmxappbackend.repository.AthleteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Author: Mortada M'Rabet & Sem Huits
 * @Description: This class manages every mapping, which allows for data manipulation
 */

@RestController
@RequestMapping("/athlete")
public class AthleteController {

    private final AthleteRepository athleteRepository;

    public AthleteController(AthleteRepository AthleteRepository) {
        this.athleteRepository = AthleteRepository;
    }

    /**
     * @return an iterable list of rolls
     */
    @GetMapping
    public @ResponseBody
    Iterable<Athlete> getAll(){
        return athleteRepository.findAll();
    }
    /**
     * @param id
     * @return a roll by its ID
     */
    @GetMapping(path = "/{id}")
    public Athlete getAthlete(@PathVariable int id){
        Athlete foundAthlete = athleteRepository.findById(id);

        if(foundAthlete == null) throw new ResourceNotFoundException("Could not find athlete with id:" + id);

        return foundAthlete;
    }

    /**
     * @param Athlete
     * @return a response status whether the operation to add a roll was succesfull
     */
    @PostMapping
    public ResponseEntity<Athlete> createAthlete(@RequestBody Athlete Athlete){
        Athlete toBeinsertedAthlete= athleteRepository.save(Athlete);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(toBeinsertedAthlete.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    /**
     * @param id
     * @return a response status whether the delete operation was succesfull
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Athlete> deleteAthlete(@PathVariable int id){
        Athlete toBeDeletedAthlete = athleteRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedAthlete);
    }
}

