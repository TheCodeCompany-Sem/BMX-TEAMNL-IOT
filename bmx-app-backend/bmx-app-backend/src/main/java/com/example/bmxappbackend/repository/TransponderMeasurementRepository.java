package com.example.bmxappbackend.repository;

import com.example.bmxappbackend.model.TransponderMeasurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransponderMeasurementRepository extends JpaRepository<TransponderMeasurement, Integer> {
    List<TransponderMeasurement> findAll();

    TransponderMeasurement findById(int id);

    TransponderMeasurement save(TransponderMeasurement transponder);

    TransponderMeasurement deleteById(int id);

    @Query(value = "SELECT * FROM transponder_measurement WHERE transponder_code = ?",
    nativeQuery = true)
    List<TransponderMeasurement> getAllByTransponderCode(String transponderCode);
}
