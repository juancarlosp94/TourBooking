package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.RatingDto;
import com.group5.tourbooking.dto.RatingDtoId;
import com.group5.tourbooking.model.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    Rating saveRating(Rating rating);
    List<RatingDto> findAllRating();
    RatingDto findRatingById(Long id);
    RatingDto updateRating(Rating rating);
    void deleteRatingById(Long id);
    public RatingDto createRatingWithId(RatingDtoId ratingDtoId);
}
