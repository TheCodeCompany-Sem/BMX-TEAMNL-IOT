package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.exceptions.ResourceNotFoundException;
import com.example.bmxappbackend.model.TransponderMeasurement;
import com.example.bmxappbackend.repository.TransponderMeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * @Author: Mortada M'Rabet
 * @Description: This class was made to catch data from the MyLaps system
 */
@RestController
@RequestMapping("/transponder")
public class TransponderMeasurementController {
    private final TransponderMeasurementRepository transponderMeasurementRepository;

    @Autowired
    public TransponderMeasurementController(TransponderMeasurementRepository transponderRepository) {
        this.transponderMeasurementRepository = transponderRepository;
    }

    /**
     * @return an iterable list of transponders
     */
    @GetMapping
    public @ResponseBody Iterable<TransponderMeasurement> getAll(){
        return transponderMeasurementRepository.findAll();
    }

    @GetMapping("/code")
    public @ResponseBody List<TransponderMeasurement>
    getTransponderMeasurementByTransponderCode(@RequestParam(value = "transponderCode") String transponderCode){
        return transponderMeasurementRepository.getAllByTransponderCode(transponderCode);
    }

    /**
     *
     * @param id
     * @return a transponder by its id
     */
    @GetMapping(path = "/{id}")
    public TransponderMeasurement getTransponder(@PathVariable int id){
        TransponderMeasurement foundTransponderMeasurement = transponderMeasurementRepository.findById(id);

        if(foundTransponderMeasurement == null)
            throw new ResourceNotFoundException("Could not find measurement with id:" + id);

        return foundTransponderMeasurement;
    }

    /**
     * @param transponderMeasurement
     * @return the created transponderMeasurement if it was created
     */
    @PostMapping
    public ResponseEntity<TransponderMeasurement> createTransponder(@RequestBody TransponderMeasurement transponderMeasurement){
        transponderMeasurementRepository.save(transponderMeasurement);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(transponderMeasurement.getId()).toUri();

        return ResponseEntity.created(location).body(transponderMeasurement);
    }

    /**
     *
     * @param id
     * @return the deleted transponder if it was successfully deleted
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TransponderMeasurement> deleteTransponder(@PathVariable int id){
        TransponderMeasurement toBeDeletedTransponderMeasurement = transponderMeasurementRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedTransponderMeasurement);

    }
}
