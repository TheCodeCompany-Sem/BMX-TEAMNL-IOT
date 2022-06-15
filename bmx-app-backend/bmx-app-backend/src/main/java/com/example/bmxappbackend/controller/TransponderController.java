package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.exceptions.ResourceNotFoundException;
import com.example.bmxappbackend.model.Transponder;
import com.example.bmxappbackend.repository.TransponderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Author: Mortada M'Rabet
 * @Description: This class was made to catch data from the MyLaps system
 */
@RestController
@RequestMapping("/transponder")
public class TransponderController {
    private final TransponderRepository transponderRepository;

    @Autowired
    public TransponderController(TransponderRepository transponderRepository) {
        this.transponderRepository = transponderRepository;
    }

    /**
     * @return an iterable list of transponders
     */
    @GetMapping
    public @ResponseBody Iterable<Transponder> getAll(){
        return transponderRepository.findAll();
    }

    /**
     *
     * @param id
     * @return a transponder by its id
     */
    @GetMapping(path = "/{id}")
    public Transponder getTransponder(@PathVariable int id){
        return transponderRepository.findById(id);
    }

    /**
     * @param transponder
     * @return the created transponder if it was created
     */
    @PostMapping
    public ResponseEntity<Transponder> createTransponder(@RequestBody Transponder transponder){
        transponderRepository.save(transponder);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transponder.getId()).toUri();

        return ResponseEntity.created(location).body(transponder);
    }

    /**
     *
     * @param id
     * @return the deleted transponder if it was successfully deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Transponder> deleteTransponder(@PathVariable int id){
        Transponder toBeDeletedTransponder = transponderRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedTransponder);

    }
}
