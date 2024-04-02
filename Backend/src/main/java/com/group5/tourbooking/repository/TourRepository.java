package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Tour;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour, Long> {
    @EntityGraph(attributePaths = "images")
    @Override
    List<Tour> findAll();
    Optional<Tour> findByName(String name);
    List<Tour> findToursByCategoryId(Long categoryId);
    List<Tour> findByNameContainingIgnoreCase(String name);
    List<Tour> findTop5ByNameContainingIgnoreCase(String name);

}
