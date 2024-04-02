package com.group5.tourbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "rating")
@Data
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRating;

    @OneToOne
    @JoinColumn(name = "idReserva")
    @JsonManagedReference
    @JsonIgnore
    private Reservation reservation;

    @Column(nullable = false)
    private int ratingValue;

    @Column(length = 1000)
    private String comments;

    public Rating(Long idRating, Reservation reservation, int ratingValue, String comments) {
        this.idRating = idRating;
        this.reservation = reservation;
        this.ratingValue = ratingValue;
        this.comments = comments;
    }

    public Rating() {
    }

    public Long getUsuarioId() {
        if (reservation != null && reservation.getUsuario() != null) {
            return reservation.getUsuario().getId();
        }
        return null;
    }


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
