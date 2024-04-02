package com.group5.tourbooking.dto;

import java.time.LocalDate;

public class ReservationDtoId {
    private Long tourId;
    private Long usuarioId;
    private LocalDate date;
    private int tickets;

    public ReservationDtoId() {
    }

    public ReservationDtoId(Long tourId, Long usuarioId, LocalDate date, int tickets) {
        this.tourId = tourId;
        this.usuarioId = usuarioId;
        this.date = date;
        this.tickets = tickets;
    }

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "ReservationDtoId{" +
                "tourId=" + tourId +
                ", usuarioId=" + usuarioId +
                ", date=" + date +
                ", tickets=" + tickets +
                '}';
    }
}
