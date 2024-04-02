package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.RatingCommentsDto;
import com.group5.tourbooking.dto.RatingStatsDTO;
import com.group5.tourbooking.exception.GlobalExceptionHandler;
import com.group5.tourbooking.exception.ResourceNotFoundException;
import com.group5.tourbooking.exception.SameNameExeption;
import com.group5.tourbooking.model.Reservation;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import com.group5.tourbooking.repository.TourRepository;
import com.group5.tourbooking.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService{
    @Autowired
    private TourRepository tourRepository;
    @Autowired
    GlobalExceptionHandler globalExceptionHandler;
    @Autowired
    UsuarioRepository usuarioRepository;
    private static final Logger logger = LogManager.getLogger(TourServiceImpl.class);
    @Transactional
    @Override
    public Tour createTour(Tour tour) {
        Optional<Tour> existingTour= tourRepository.findByName(tour.getName());

        if(existingTour.isPresent()){
            throw new SameNameExeption();
        }
        return tourRepository.save(tour);
    }

    @Transactional
    @Override
    public List<Tour> findAllTours() {
        List<Tour> tours = tourRepository.findAll();
        if(tours.isEmpty()){
            throw new EntityNotFoundException("No Tour found");
        }
        return tours;

    }

            @Override
            public Tour findTourById(Long id) {
                return tourRepository.findById(id)
                        .orElseThrow(()-> new EntityNotFoundException("Can´t find Tour with ID: "+id));
            }

    @Override
    public List<Tour> findToursByIds(List<Long> ids) {
        return tourRepository.findAllById(ids);
    }

    @Override
    public Tour updateTour(Tour tour) {
        return null;
    }

    @Override
    public Tour updateTour(Long id, Tour updatedTour) {
        return tourRepository.findById(id).map(tour -> {
            if (updatedTour.getName() != null) {
                tour.setName(updatedTour.getName());
            }
            if (updatedTour.getDescription() != null) {
                tour.setDescription(updatedTour.getDescription());
            }
            if (updatedTour.getShortDescription() != null) {
                tour.setShortDescription(updatedTour.getShortDescription());
            }
            if (updatedTour.getPrice() != null) {
                tour.setPrice(updatedTour.getPrice());
            }
            if (updatedTour.getCategory() != null) {
                tour.setCategory(updatedTour.getCategory());
            }
            if (updatedTour.getCharacteristics() != null && !updatedTour.getCharacteristics().isEmpty()) {
                tour.setCharacteristics(updatedTour.getCharacteristics());
            }
            if(updatedTour.getUbication()!= null){
                tour.setUbication(updatedTour.getUbication());
            }
            return tourRepository.save(tour);
        }).orElseThrow(() -> new ResourceNotFoundException("Tour not found with id " + id));
    }

    @Override
    public void deleteTour(Long id) {

        tourRepository.deleteById(id);
    }

    @Override
    public List<Tour> findToursByCategoryId(Long categoryId) {
        return tourRepository.findToursByCategoryId(categoryId);
    }

    @Override
    public List<Tour> findByNameContainingIgnoreCase(String name) {
        return tourRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Tour> findTop5ByNameContainingIgnoreCase(String name) {
        return tourRepository.findTop5ByNameContainingIgnoreCase(name);
    }

    public RatingStatsDTO getRatingStats(Long tourId) {
        Optional<Tour> optionalTour = tourRepository.findById(tourId);
        if (!optionalTour.isPresent()) {
            throw new EntityNotFoundException("Tour not found with ID: " + tourId);
        }
        Tour tour = optionalTour.get();
        if (tour.getReservations().isEmpty()) {
            throw new EntityNotFoundException("No se han encotrado reservaciones para el Tour");
        }
        return tour.getRatingStats();
    }

    public List<RatingCommentsDto> getRatingComments(Long tourId){
        Optional<Tour> optionalTour= tourRepository.findById(tourId);
        if (!optionalTour.isPresent()) {
            throw new EntityNotFoundException("Tour not found with ID: " + tourId);
        }
        Tour tour = optionalTour.get();
        if (tour.getReservations().isEmpty()) {
            throw new EntityNotFoundException("No se han encotrado reservaciones para el Tour");
        }
        List<Reservation> reservations=tour.getReservations();
        return getRatingCommentsTour(reservations);
    }

    public List<RatingCommentsDto> getRatingCommentsTour(List<Reservation> reservations){
        List<RatingCommentsDto> commentsDtoList= new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getRating() != null) {
                RatingCommentsDto ratingCommentsDto= new RatingCommentsDto();
                Long idUsuario= reservation.getRating().getUsuarioId();

                ratingCommentsDto.setUsuarioId(idUsuario);  // Corrección aquí
                logger.info("ID USUARIO DEL COMMENT: "+ idUsuario);
                ratingCommentsDto.setUserName(getUsernameByID(idUsuario));
                ratingCommentsDto.setTourID(reservation.getTour().getId());
                ratingCommentsDto.setComment(reservation.getRating().getComments());

                commentsDtoList.add(ratingCommentsDto);
                logger.info("DTO: "+ ratingCommentsDto);
            }
        }
        return commentsDtoList;
    }

    public String getUsernameByID(Long id){
        Optional<Usuario> usuario= usuarioRepository.findById(id);
        String userName= usuario.get().getUsername();
        return userName;
    }
}
