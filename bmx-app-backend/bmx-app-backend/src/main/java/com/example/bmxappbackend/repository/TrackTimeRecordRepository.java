package com.example.bmxappbackend.repository;

import com.example.bmxappbackend.model.TrackTimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackTimeRecordRepository extends JpaRepository<TrackTimeRecord, Integer> {

    List<TrackTimeRecord> findAll();

    TrackTimeRecord findById(int id);

    TrackTimeRecord save(TrackTimeRecord trackTimeRecord);

    TrackTimeRecord deleteById(int id);

}
