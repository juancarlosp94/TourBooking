package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
