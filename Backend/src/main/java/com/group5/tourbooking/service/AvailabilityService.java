package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.AvailabilityDTO;
import com.group5.tourbooking.dto.TourDto;
import com.group5.tourbooking.mapper.TourMapper;
import com.group5.tourbooking.model.Schedule;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.repository.ReservationRepository;
import com.group5.tourbooking.repository.ScheduleRepository;
import com.group5.tourbooking.repository.TourRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AvailabilityService {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private TourMapper tourMapper;
    @Autowired
    private TourRepository tourRepository;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityService.class);


    public AvailabilityDTO getAvailability(Long tourId, LocalDate date) {
        Schedule schedule = scheduleRepository.findByTourId(tourId);
        if (!schedule.getDaysOfWeek().contains(date.getDayOfWeek())) {
            return new AvailabilityDTO(date, 0);
        }

        int reservationsCount = reservationRepository.countByTourIdAndDate(tourId, date);
        int availableSlots = schedule.getMaxAvailability() - reservationsCount;

        return new AvailabilityDTO(date, availableSlots);
    }
    public AvailabilityDTO getAvailability1(Long tourId, LocalDate date) {
        Schedule schedule = scheduleRepository.findByTourId(tourId);
        logger.info("SCHEDULE: "+schedule);
        logger.info("DAYS OF WEEK: "+schedule.getDaysOfWeek());

        int reservationsCount = reservationRepository.countByTourIdAndDate(tourId, date);
        logger.info("RESERVATIONS COUNT: "+reservationsCount);
        int availableSlots = schedule.getMaxAvailability() - reservationsCount;
        logger.info("AVAILABLE SLOTS: "+ availableSlots);
        logger.info("DATE: "+ date);

        if (!schedule.getDaysOfWeek().contains(date.getDayOfWeek())) {
            AvailabilityDTO availabilityDTO=new AvailabilityDTO();
            availabilityDTO.setAvailableSlots(0);
            availabilityDTO.setDate(date);
            return availabilityDTO;
        }

        AvailabilityDTO availabilityDTO= new AvailabilityDTO();
        availabilityDTO.setAvailableSlots(availableSlots);
        availabilityDTO.setDate(date);

        return availabilityDTO;
    }

    public List<TourDto> isAvailabilityByTicketsAndDate(
            List<Long> ids, int adultsTickets, int kidsTickets, LocalDate date){

        List<Long> idsToursAvailable = new ArrayList<>();
        int totalTicketsRequired = adultsTickets + kidsTickets;

        // Filtra los IDs basándote en la disponibilidad
        for(Long id : ids) {
            if(getAvailabilityBoolean(id, date, totalTicketsRequired)){
                idsToursAvailable.add(id);
            }
        }
        logger.info("LIST DE Availability :: " + idsToursAvailable);
        // Suponiendo que tengas un método que, dado un ID, te devuelve el TourDto
        // Convertir idsToursAvailable en una lista de TourDto
        List<TourDto> tourDtoList=idsToursAvailable.stream()
                .map(this::getTourDtoById)
                .collect(Collectors.toList());
        logger.info("DTO LIST AVAILABLE :::" + tourDtoList + "::::::::::::::::::::::::::::::::::::");
        return tourDtoList;

    }

    private boolean getAvailabilityBoolean(Long id, LocalDate date, int tickets){
        AvailabilityDTO availabilityDTO= this.getAvailability1(id, date);

        if (availabilityDTO.getAvailableSlots() > tickets){
            return true;
        }else{
            return false;
        }
    }

    // Suponiendo que este método exista en tu servicio
    private TourDto getTourDtoById(Long id) {
        Tour tour = tourRepository.findById(id).orElse(null);
        return tourMapper.tourToDto(tour); // Suponiendo que tienes un método que convierte la entidad Tour en TourDto
    }


    /*

        public List<TourDto> isAvailabilityByTicketsAndDate(
            List<Long> ids, int adultsTickets, int kidsTickets, LocalDate date){

        List<Long> idsToursAvailable = new ArrayList<>();
        int totalTicketsRequired = adultsTickets + kidsTickets;

        // Filtra los IDs basándote en la disponibilidad
        for(Long id : ids) {
            AvailabilityDTO availability = getAvailability(id, date);
            if(availability.getAvailableSlots() >= totalTicketsRequired) {
                idsToursAvailable.add(id);
            }
        }
        logger.info("LIST DE Availability :: " + idsToursAvailable);
        // Suponiendo que tengas un método que, dado un ID, te devuelve el TourDto
        // Convertir idsToursAvailable en una lista de TourDto
        List<TourDto> tourDtoList=idsToursAvailable.stream()
                .map(this::getTourDtoById)
                .collect(Collectors.toList());
        logger.info("DTO LIST AVAILABLE :::" + tourDtoList + "::::::::::::::::::::::::::::::::::::");
        return tourDtoList;

    }

    // Suponiendo que este método exista en tu servicio
    private TourDto getTourDtoById(Long id) {
        Tour tour = tourRepository.findById(id).orElse(null);
        return tourMapper.tourToDto(tour); // Suponiendo que tienes un método que convierte la entidad Tour en TourDto
    }
}
     */
}