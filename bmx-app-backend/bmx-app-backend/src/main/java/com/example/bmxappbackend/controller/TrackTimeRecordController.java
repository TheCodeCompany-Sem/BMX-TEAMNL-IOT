package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.model.TrackTimeRecord;
import com.example.bmxappbackend.repository.TrackTimeRecordRepository;
import com.example.bmxappbackend.views.TrackTimeRecordView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * @Author: Mortada M'Rabet
 * @Description: This class manages every mapping, which allows for data manipulation
 */

@RestController
@RequestMapping("/TrackTimeRecord")
public class TrackTimeRecordController {

    private final TrackTimeRecordRepository trackTimeRecordRepository;

    @Autowired
    public TrackTimeRecordController(TrackTimeRecordRepository trackTimeRecordRepository) {
        this.trackTimeRecordRepository = trackTimeRecordRepository;
    }

    /**
     * @return an iterable list of rolls
     */
    @JsonView({TrackTimeRecordView.base.class})
    @GetMapping
    public @ResponseBody Iterable<TrackTimeRecord> getAll(){
        return trackTimeRecordRepository.findAll();
    }
    /**
     * @param id
     * @return a roll by its ID
     */
    @GetMapping(path = "/{id}")
    public TrackTimeRecord getTrackTimeRecord(@PathVariable int id){
        return trackTimeRecordRepository.findById(id);
    }

    /**
     * @param trackTimeRecord
     * @return a response status whether the operation to add a roll was succesfull
     */
    @PostMapping
    public ResponseEntity<TrackTimeRecord> createTrackTimeRecord(@RequestBody TrackTimeRecord trackTimeRecord){
        trackTimeRecordRepository.save(trackTimeRecord);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(trackTimeRecord.getId()).toUri();

        return ResponseEntity.created(location).body(trackTimeRecord);
    }

    /**
     * @param id
     * @return a response status whether the delete operation was succesfull
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<TrackTimeRecord> deleteRoll(@PathVariable int id){
        TrackTimeRecord toBeDeletedTrackTimeRecord = trackTimeRecordRepository.deleteById(id);

        return ResponseEntity.ok(toBeDeletedTrackTimeRecord);
    }
}
