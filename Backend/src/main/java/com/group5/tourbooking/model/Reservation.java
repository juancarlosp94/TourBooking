package com.group5.tourbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReservation;
    @ManyToOne
    @JoinColumn(name = "idTour")
    @JsonManagedReference
    @JsonIgnore
    private Tour tour;
    @ManyToOne
    @JoinColumn(name = "idUsuario")
    @JsonManagedReference
    @JsonIgnore
    private Usuario usuario;
    @Column
    private LocalDate date;
    @Column
    private Integer tickets;

    @OneToOne(mappedBy = "reservation", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private Rating rating;

    public void add(Reservation reservation) {
    }

    public Reservation() {
    }

    public Reservation(Long idReservation, Tour tour, Usuario usuario, LocalDate date, int tickets, Rating rating) {
        this.idReservation = idReservation;
        this.tour = tour;
        this.usuario = usuario;
        this.date = date;
        this.tickets = tickets;
        this.rating = rating;
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
        return "Reservation{" +
                "idReservation=" + idReservation +
                ", tour=" + tour +
                ", usuario=" + usuario +
                ", date=" + date +
                ", tickets=" + tickets +
                ", rating=" + rating +
                '}';
    }
}

