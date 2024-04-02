package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.AvailabilityDTO;
import com.group5.tourbooking.dto.ReservationDto;
import com.group5.tourbooking.dto.ReservationDtoId;
import com.group5.tourbooking.mapper.ReservationMapper;
import com.group5.tourbooking.model.Reservation;
import com.group5.tourbooking.model.Schedule;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import com.group5.tourbooking.repository.ReservationRepository;
import com.group5.tourbooking.repository.ScheduleRepository;
import com.group5.tourbooking.repository.TourRepository;
import com.group5.tourbooking.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    TourRepository tourRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AvailabilityService availabilityService;
    @Autowired
    ScheduleRepository scheduleRepository;

    private static final Logger logger = LogManager.getLogger(ReservationServiceImpl.class);

    @Override
    public Reservation createReservation(Reservation reservation) {

        return reservationRepository.save(reservation);
    }

//    @Override
//    public ReservationDto createReservationWithId(ReservationDtoId reservationDtoId) {
//        {
//            Tour tour = tourRepository.findById(reservationDtoId.getTourId()).orElseThrow(() -> new RuntimeException("Tour no encontrado"));
//            Usuario usuario = usuarioRepository.findById(reservationDtoId.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//            Reservation reservation = new Reservation();
//            reservation.setTour(tour);
//            reservation.setUsuario(usuario);
//            reservationRepository.save(reservation);
//            ReservationDto reservationDto= reservationMapper.reservationToDto(reservation);
//            return reservationDto;
//        }
//    }

       @Override
    public ReservationDto createReservationWithId(ReservationDtoId reservationDtoId) {
        {
            Tour tour = tourRepository.findById(reservationDtoId.getTourId()).orElseThrow(() -> new RuntimeException("Tour no encontrado"));
            Usuario usuario = usuarioRepository.findById(reservationDtoId.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            AvailabilityDTO availabilityDTO= availabilityService.getAvailability1(reservationDtoId.getTourId(), reservationDtoId.getDate());
            int tickets= reservationDtoId.getTickets();

            LocalDate date = reservationDtoId.getDate();
                logger.info("SLOT::::::::::: "+availabilityDTO.getAvailableSlots());
                logger.info("TICKETS:::::::: "+tickets);
            if(availabilityDTO.getAvailableSlots() < tickets){
                throw new RuntimeException("No hay cupo para el Tour");
            }
            if(date == null) {
                throw new RuntimeException("Fecha no válida o no hay slots disponibles.");
            }

            Reservation reservation = new Reservation();
            reservation.setTour(tour);
            reservation.setUsuario(usuario);
            reservation.setDate(date);
            reservation.setTickets(tickets);
            System.out.println("LA DATE antes de reservationRepository.save: "+date);
            reservationRepository.save(reservation);
            ReservationDto reservationDto= reservationMapper.reservationToDto(reservation);
            System.out.println("DATE DE RESERVATION DTO: "+ reservationDto.getDate());

            Schedule schedule= scheduleRepository.findByTourId(tour.getId());
            int disponibility= schedule.getMaxAvailability();
            int actualDisponibility= disponibility-=tickets;

            return reservationDto;
        }
    }

    @Override
    public List<Reservation> findAllReservation() {
        List<Reservation> reservations= reservationRepository.findAll();
        if (reservations.isEmpty()){
            throw new EntityNotFoundException("No se encontraron Reservaciones");
        }
        return reservations;
    }

    @Override
    public Optional<Reservation> findReservationById(Long id) {
        Optional<Reservation> reservation= reservationRepository.findById(id);
        if (reservation.isEmpty()){
            throw new EntityNotFoundException("La Reservacion con el id: "+id+" no se ha encontrado");
        }
        return reservation;
    }

    @Override
    public Reservation updateReservation(Reservation updatedReservation) {
        if (updatedReservation.getIdReservation() == null) {
            throw new IllegalArgumentException("La Reservacion a actualizar debe tener un ID");
        }
        Reservation existingReservation= reservationRepository.findById(updatedReservation.getIdReservation())
                .orElseThrow(()-> new EntityNotFoundException("No se ha encontrado la reservacion a modificar"));

        existingReservation.setIdReservation(updatedReservation.getIdReservation());
        existingReservation.setTour(updatedReservation.getTour());
        existingReservation.setUsuario(updatedReservation.getUsuario());
        existingReservation.setRating(updatedReservation.getRating());
        reservationRepository.save(existingReservation);
        return existingReservation;
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public List<ReservationDto> findReservationsByTourId(Long tourId) {
        List<Reservation> reservations = reservationRepository.findByTourId(tourId);
        if (reservations.isEmpty()){
            throw new EntityNotFoundException("No se han encontrado reservaciones para el Tour indicado");
        }

        return reservations.stream()
                .map(reservationMapper::reservationToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReservationDto> findReservationsByUsuarioId(Long usuarioId) {
        List<Reservation> reservations = reservationRepository.findByUsuarioId(usuarioId);
        return reservations.stream()
                .map(reservationMapper::reservationToDto)
                .collect(Collectors.toList());
    }
}
