package com.group5.tourbooking.controller;

import com.group5.tourbooking.dto.AvailabilityDTO;
import com.group5.tourbooking.dto.TourDto;
import com.group5.tourbooking.service.AvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/availability")
@CrossOrigin(origins = "*")
public class AvailabilityController {

    @Autowired
    private AvailabilityService availabilityService;

    @GetMapping("/{tourId}")
    public ResponseEntity<AvailabilityDTO> getAvailability(
            @PathVariable Long tourId, @RequestParam LocalDate date) {
        AvailabilityDTO availability = availabilityService.getAvailability1(tourId, date);
        return ResponseEntity.ok(availability);
    }

    @GetMapping("/byticketanddate")
    public ResponseEntity<List<TourDto>> getToursByAvailability(
            @RequestParam List<Long> ids,
            @RequestParam int adultsTickets,
            @RequestParam int kidsTickets,
            @RequestParam LocalDate date) {

        List<TourDto> availableTours = availabilityService.isAvailabilityByTicketsAndDate(
                ids, adultsTickets, kidsTickets, date);

        if (availableTours.isEmpty()) {
            return ResponseEntity.noContent().build(); // Si no hay tours disponibles, devolvemos un 204 No Content
        }

        return ResponseEntity.ok(availableTours); // Si hay tours, devolvemos un 200 OK con la lista de tours
    }
}

