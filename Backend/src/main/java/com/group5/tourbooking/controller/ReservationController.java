package com.group5.tourbooking.controller;

import com.group5.tourbooking.dto.ReservationDto;
import com.group5.tourbooking.dto.ReservationDtoId;
import com.group5.tourbooking.mapper.ReservationMapper;
import com.group5.tourbooking.model.Reservation;
import com.group5.tourbooking.repository.ReservationRepository;
import com.group5.tourbooking.service.ReservationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    ReservationMapper reservationMapper;
    @Autowired
    private ReservationService reservationService;

    //CREATE
    @PostMapping
    public ResponseEntity<Void> createReservation(@RequestBody ReservationDtoId reservationDtoId) {
        reservationService.createReservationWithId(reservationDtoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    //READ
    @GetMapping
    public List<ReservationDto> findAllReservations(){
        List<Reservation> reservations= reservationService.findAllReservation();
        if (reservations.isEmpty()){
            throw new EntityNotFoundException("No se encontraron Reservaciones");
        }

        List<ReservationDto> reservationDto=  reservations.stream()
                .map(reservationMapper::reservationToDto)
                .collect(Collectors.toList());
        return reservationDto;
    }

    //READ BY ID
    @GetMapping("/byid/{id}")
    public ReservationDto findReservationByTd(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.findReservationById(id);
        if (reservation.isEmpty()) {
            throw new EntityNotFoundException("No se ha encontrado Reservacion con el id " + id);
        }
        ReservationDto reservationDto = reservationMapper.reservationToDto(reservation.get());

        return reservationDto;
    }

    ///READ BY TOUR'S ID
    @GetMapping("/bytourid/{id}")
    public List<ReservationDto> findByTourId(@PathVariable Long id) {
        return reservationService.findReservationsByTourId(id);
    }

    //READ BY USUARIO
    @GetMapping("/byusuarioid/{id}")
    public List<ReservationDto> findByUsuarioId(@Valid @PathVariable Long id) {
        return reservationService.findReservationsByUsuarioId(id);
    }

    //UPADATE
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable Long id, @RequestBody Reservation updatedReservation) {
        if (!id.equals(updatedReservation.getIdReservation())) {
            throw new RuntimeException("Id de PahtVariable no coincide con el del RequestBody");
        }
        try {
            Reservation reservation = reservationService.updateReservation(updatedReservation);
            return new ResponseEntity<>(reservation, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            throw  new IllegalArgumentException(e);
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("No se ha encontrado la Reservacion indicada");
        }
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteReservationById(@PathVariable Long id){

        reservationService.deleteReservationById(id);
    }
}
