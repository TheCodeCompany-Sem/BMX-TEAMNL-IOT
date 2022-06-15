package com.example.bmxappbackend.repository;

import com.example.bmxappbackend.model.Transponder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransponderRepository extends JpaRepository<Transponder, Integer> {
    List<Transponder> findAll();

    Transponder findById(int id);

    Transponder save(Transponder transponder);

    Transponder deleteById(int id);
}
