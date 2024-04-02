package com.group5.tourbooking.service;

import com.group5.tourbooking.dto.RatingDto;
import com.group5.tourbooking.dto.RatingDtoId;
import com.group5.tourbooking.dto.ReservationDto;
import com.group5.tourbooking.dto.ReservationDtoId;
import com.group5.tourbooking.mapper.RatingMapper;
import com.group5.tourbooking.model.Rating;
import com.group5.tourbooking.model.Reservation;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Usuario;
import com.group5.tourbooking.repository.RatingRepository;
import com.group5.tourbooking.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RatingServiceImpl implements RatingService{
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RatingMapper ratingMapper;
    @Autowired
    ReservationRepository reservationRepository;

    @Override
    public Rating saveRating(Rating rating) {

        return ratingRepository.save(rating);
    }

    @Override
    public RatingDto createRatingWithId(RatingDtoId ratingDtoId) {
        {
            Reservation reservation= reservationRepository.findById(ratingDtoId.getIdReservation())
                    .orElseThrow(()-> new EntityNotFoundException("Reservacion no encontrada"));

            Rating rating= new Rating();
            rating.setReservation(reservation);
            rating.setRatingValue(ratingDtoId.getRatingValue());
            rating.setComments(ratingDtoId.getComments());
            ratingRepository.save(rating);
            RatingDto ratingDto= ratingMapper.ratingToDto(rating);

            return ratingDto;

        }
    }

    @Override
    public List<RatingDto> findAllRating() {
        List<Rating> rating=ratingRepository.findAll();
        if (rating.isEmpty()){
            throw new EntityNotFoundException("No se ha encontrado Rating");
        }
        List<RatingDto> ratingDtos= rating.stream()
                .map(ratingMapper::ratingToDto)
                .collect(Collectors.toList());

        return ratingDtos;
    }

    @Override
    public RatingDto findRatingById(Long id) {
        Optional<Rating> rating= ratingRepository.findById(id);
        if (!rating.isPresent()){
            throw new EntityNotFoundException("Rating no encontrado");
        }
        RatingDto ratingDto= ratingMapper.ratingToDto(rating.get());
        return ratingDto;
    }

    @Override
    public RatingDto updateRating(Rating updatedRating) {
        if (updatedRating.getIdRating() == null) {
            throw new IllegalArgumentException("El Rating a actualizar debe tener un ID");
        }
        Rating existingRating = ratingRepository.findById(updatedRating.getIdRating())
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el Rating a modificar"));

        existingRating.setIdRating(updatedRating.getIdRating());
        existingRating.setReservation(updatedRating.getReservation());
        existingRating.setRatingValue(updatedRating.getRatingValue());
        existingRating.setComments(updatedRating.getComments());
        ratingRepository.save(existingRating);
        RatingDto ratingDto= ratingMapper.ratingToDto(existingRating);
        return ratingDto;
    }

    @Override
    public void deleteRatingById(Long id) {
        ratingRepository.deleteById(id);
    }
}
