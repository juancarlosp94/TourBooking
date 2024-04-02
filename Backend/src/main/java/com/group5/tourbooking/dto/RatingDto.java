package com.group5.tourbooking.dto;

import com.group5.tourbooking.model.Reservation;
import lombok.Data;

@Data
public class RatingDto {
    private Long idRating;
    private Reservation reservation;
    private int ratingValue;
    private String comments;

    public Long getIdRating() {
        return idRating;
    }

    public void setIdRating(Long idRating) {
        this.idRating = idRating;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public int getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(int ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
