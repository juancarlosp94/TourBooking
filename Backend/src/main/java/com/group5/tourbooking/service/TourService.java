package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.RatingCommentsDto;
import com.group5.tourbooking.dto.RatingStatsDTO;
import com.group5.tourbooking.model.Tour;

import java.util.List;

public interface TourService {
    Tour createTour(Tour tour);
    List<Tour> findAllTours();
    Tour findTourById(Long id);
    List<Tour> findToursByIds(List<Long> ids);
    Tour updateTour(Tour tour);
    Tour updateTour(Long id, Tour updatedTour);
    void deleteTour(Long id);
    List<Tour> findToursByCategoryId(Long categoryId);
    List<Tour> findByNameContainingIgnoreCase(String name);
    List<Tour> findTop5ByNameContainingIgnoreCase(String name);
    RatingStatsDTO getRatingStats(Long tourId);
    List<RatingCommentsDto> getRatingComments(Long tourId);
}
