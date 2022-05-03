package com.example.bmxappbackend.repository;

import com.example.bmxappbackend.model.Athlete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AthleteRepository extends JpaRepository<Athlete, Integer> {
    List<Athlete> findAll();

    Athlete findById(int id);

    Athlete save(Athlete athlete);

    Athlete deleteById(int id);
}
