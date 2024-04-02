package com.group5.tourbooking.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group5.tourbooking.model.Rating;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    private Long idReservation;
    @JsonManagedReference
    private Tour tour;
    @JsonManagedReference
    private Usuario usuario;
    private LocalDate date;
    private Rating rating;
    private Integer tickets;

    public ReservationDto() {
    }

    public ReservationDto(Long idReservation, Tour tour, Usuario usuario, LocalDate date, Rating rating, Integer tickets) {
        this.idReservation = idReservation;
        this.tour = tour;
        this.usuario = usuario;
        this.date = date;
        this.rating = rating;
        this.tickets = tickets;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getTickets() {
        return tickets;
    }

    public void setTickets(Integer tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "ReservationDto{" +
                "idReservation=" + idReservation +
                ", tour=" + tour +
                ", usuario=" + usuario +
                ", date=" + date +
                ", rating=" + rating +
                ", tickets=" + tickets +
                '}';
    }
}
