package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.*;
import com.group5.tourbooking.model.*;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.Mapping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Mapper
public class TourMapper {
    @Autowired
    CharacteristicMapper characteristicMapper;
    @Autowired
    ReservationMapper reservationMapper;

    public TourDto tourToDto(Tour tour) {
        TourDto dto = new TourDto();
        dto.setId(tour.getId());
        dto.setName(tour.getName());
        dto.setDescription(tour.getDescription());
        dto.setShortDescription(tour.getShortDescription());
        dto.setPrice(tour.getPrice());
        dto.setCategory(categoryToCategoryDto(tour.getCategory()));
        dto.setUbication(tour.getUbication());

        List<CharacteristicDto> characteristicDtos = tour.getCharacteristics().stream()
                .map(characteristicMapper::characteristicToDto)
                .collect(Collectors.toList());
        dto.setCharacteristics(characteristicDtos);

        List<ImageDto> imageDtos = tour.getImages().stream()
                .map(this::imageToDto)
                .collect(Collectors.toList());
        dto.setImages(imageDtos);

//        List<ReservationDto> reservationDtos= tour.getReservations().stream()
//                .map(reservationMapper::reservationToDto)
//                .collect(Collectors.toList());
//        dto.setReservations(reservationDtos);
        return dto;
    }
    private CategoryDto categoryToCategoryDto(Category category) {
        if (category == null) {
            return null;  // o puedes devolver una instancia vacía de CategoryDto si prefieres
        }
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setUrlCategoryImage(category.getUrlCategoryImage());
        //... setea otras propiedades si las hay

        return categoryDto;
    }
    public Tour dtoToTour(TourDto dto) {
        Tour tour = new Tour();
        tour.setName(dto.getName());
        tour.setDescription(dto.getDescription());
        tour.setShortDescription(dto.getShortDescription());
        tour.setPrice(dto.getPrice());
        tour.setCategory(categoryDtoToCategory(dto.getCategory()));
        tour.setUbication(dto.getUbication());
        List<Characteristic> characteristics = dto.getCharacteristics().stream()
                .map(characteristicMapper::dtoToCharacteristic)
                .collect(Collectors.toList());
        tour.setCharacteristics(characteristics);

        List<Image> images = dto.getImages().stream()
                .map(this::dtoToImage)
                .collect(Collectors.toList());
        images.forEach(tour::addImage);

//        List <ReservationDto> dtoReservations = Collections.singletonList((ReservationDto) dto.getReservations());
//        Reservation reservations = new Reservation();
//
//        for (ReservationDto reservationDto : dtoReservations) {
//            Reservation reservation = reservationMapper.dtoToReservation(reservationDto);
//            reservations.add(reservation);
//        }
//
//        tour.setReservations((List<Reservation>) reservations);

        return tour;
    }
    public Category categoryDtoToCategory(CategoryDto categoryDto) {
        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setUrlCategoryImage(categoryDto.getUrlCategoryImage());
        //... establece otras propiedades si las hay

        return category;
    }



    public ImageDto imageToDto(Image image) {
        System.out.println("Mapeando imagen: " + image);
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setTourId(image.getTour().getId());  // Aquí estableces el tourId
        //...
        return dto;
    }

    public Image dtoToImage(ImageDto dto) {
        Image image = new Image();
        image.setUrl(dto.getUrl());

        Tour tour = new Tour();
        tour.setId(dto.getTourId());  // Aquí estableces el id del tour
        image.setTour(tour);  // Y aquí estableces el tour de la imagen
        //...
        return image;
    }
}

