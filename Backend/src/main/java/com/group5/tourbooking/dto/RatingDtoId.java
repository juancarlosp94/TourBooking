package com.group5.tourbooking.dto;

import com.group5.tourbooking.model.Reservation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class RatingDtoId {

    private Long idReservation;
    @Min(value = 1, message = "El valor mínimo de la calificación es 1.")
    @Max(value = 5, message = "El valor máximo de la calificación es 5.")
    private int ratingValue;
    private String comments;

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
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
