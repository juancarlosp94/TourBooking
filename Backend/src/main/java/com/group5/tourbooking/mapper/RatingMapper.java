package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.RatingDto;
import com.group5.tourbooking.model.Rating;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class RatingMapper {

    public RatingDto ratingToDto(Rating rating){
        RatingDto rantingDto=new RatingDto();
        rantingDto.setIdRating(rating.getIdRating());
        rantingDto.setReservation(rating.getReservation());
        rantingDto.setRatingValue(rating.getRatingValue());
        rantingDto.setComments(rating.getComments());

        return rantingDto;
    }

    public Rating dtoToRating(RatingDto ratingDto){
        Rating ranting=new Rating();
        ranting.setIdRating(ratingDto.getIdRating());
        ranting.setReservation(ratingDto.getReservation());
        ranting.setRatingValue(ratingDto.getRatingValue());
        ranting.setComments(ratingDto.getComments());

        return ranting;
    }
}
