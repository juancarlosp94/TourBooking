package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.ReservationDto;
import com.group5.tourbooking.model.Rating;
import com.group5.tourbooking.model.Reservation;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ReservationMapper {
    public ReservationDto reservationToDto(Reservation reservation){
        ReservationDto reservationDto= new ReservationDto();

        reservationDto.setIdReservation(reservation.getIdReservation());
        reservationDto.setTour(reservation.getTour());
        reservationDto.setUsuario(reservation.getUsuario());
        reservationDto.setDate(reservation.getDate());
        reservationDto.setRating(reservation.getRating());
        reservationDto.setTickets(reservation.getTickets());

        return reservationDto;
    }

    public Reservation dtoToReservation(ReservationDto reservationDto){
        Reservation reservation= new Reservation();

        reservation.setIdReservation(reservationDto.getIdReservation());
        reservation.setTour(reservationDto.getTour());
        reservation.setUsuario(reservationDto.getUsuario());
        reservation.setRating(reservationDto.getRating());
        reservation.setTickets(reservationDto.getTickets());

        return reservation;
    }

}
