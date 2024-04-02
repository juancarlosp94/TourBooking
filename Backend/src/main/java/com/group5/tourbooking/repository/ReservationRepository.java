package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByTourId(Long tourId);
    List<Reservation> findByUsuarioId(Long usuarioId);
    int countByTourIdAndDate(Long tourId, LocalDate date);
}
