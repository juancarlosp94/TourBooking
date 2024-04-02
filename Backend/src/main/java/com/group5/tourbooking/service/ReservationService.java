package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.ReservationDto;
import com.group5.tourbooking.dto.ReservationDtoId;
import com.group5.tourbooking.model.Reservation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    ReservationDto createReservationWithId(ReservationDtoId reservationDtoId);
    List<Reservation> findAllReservation();
    Optional<Reservation> findReservationById(Long id);
    Reservation updateReservation(Reservation reservation);
    void deleteReservationById(Long id);
    public List<ReservationDto> findReservationsByTourId(Long tourId);
    public List<ReservationDto> findReservationsByUsuarioId(Long usuarioId);
}
