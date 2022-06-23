package com.example.bmxappbackend.controller;

import com.example.bmxappbackend.model.Athlete;
import com.example.bmxappbackend.model.TrackTimeRecord;
import com.example.bmxappbackend.model.TransponderMeasurement;
import com.example.bmxappbackend.repository.AthleteRepository;
import com.example.bmxappbackend.repository.TrackTimeRecordRepository;
import com.example.bmxappbackend.repository.TransponderMeasurementRepository;
import com.example.bmxappbackend.views.TrackTimeRecordView;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * @Author: Mortada M'Rabet
 * @Description: This class manages every mapping, which allows for data manipulation
 */

@RestController
@RequestMapping("/TrackTimeRecord")
public class TrackTimeRecordController {

    private final TrackTimeRecordRepository trackTimeRecordRepository;
    private final TransponderMeasurementRepository transponderMeasurementRepository;
    private final AthleteRepository athleteRepository;

    public TrackTimeRecordController(TrackTimeRecordRepository trackTimeRecordRepository, TransponderMeasurementRepository transponderMeasurementRepository, AthleteRepository athleteRepository) {
        this.trackTimeRecordRepository = trackTimeRecordRepository;
        this.transponderMeasurementRepository = transponderMeasurementRepository;
        this.athleteRepository = athleteRepository;
    }

    @Autowired




    /**
     * @return an iterable list of rolls
     */
//    @JsonView({TrackTimeRecordView.base.class})
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
     * @return a response status whether the operation to add a roll was succesful
     */
    @PostMapping("/measurement/{athleteId}")
    public ResponseEntity<TrackTimeRecord> createTrackTimeRecord(@RequestBody TrackTimeRecord trackTimeRecord, @PathVariable int athleteId){
        //Get a list of transponder measurements
        //Get them with specific transponder code
        List<TransponderMeasurement> transponderMeasurementList = transponderMeasurementRepository.getAllByTransponderCode("0009992");

        System.out.println(transponderMeasurementList.toString());

        for (TransponderMeasurement transponderMeasurement : transponderMeasurementList){
            System.out.println((trackTimeRecord.getRecordedTime().until(transponderMeasurement.getTime(), ChronoUnit.SECONDS)));

            long timeDisparity = transponderMeasurement.getTime().until(trackTimeRecord.getRecordedTime(), ChronoUnit.SECONDS);

            if (timeDisparity > -7 && timeDisparity < 7 && athleteRepository.findById(athleteId).equals(transponderMeasurement.getAthlete())){
                System.out.println("yes it passes thru");
                trackTimeRecord.setAthlete(athleteRepository.findById(athleteId));
                trackTimeRecordRepository.save(trackTimeRecord);

                URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(trackTimeRecord.getId()).toUri();

                return ResponseEntity.created(location).body(trackTimeRecord);
            }
        }
        return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
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
