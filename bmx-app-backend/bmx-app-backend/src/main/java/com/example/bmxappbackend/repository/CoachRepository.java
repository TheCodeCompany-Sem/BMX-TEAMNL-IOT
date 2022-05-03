package com.example.bmxappbackend.repository;

import com.example.bmxappbackend.model.Coach;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoachRepository extends JpaRepository<Coach, Integer> {
    List<Coach> findAll();

    Coach findById(int id);

    Coach save(Coach coach);

    Coach deleteById(int id);
}
