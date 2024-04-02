package com.group5.tourbooking.dto;

import com.group5.tourbooking.model.Reservation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TourDto {
    private Long id;
    private String name;
    private String description;
    private String shortDescription;
    private BigDecimal price;
    private List<ImageDto> images;
    private CategoryDto category;
    private List<CharacteristicDto> characteristics;
    private List<PolicyDto> policies;

    private String ubication;

//    private List<Reservation> reservations;

    public List<PolicyDto> getPolicies() {
        return policies;
    }

    public void setPolicies(List<PolicyDto> policies) {
        this.policies = policies;
    }

    public List<CharacteristicDto> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<CharacteristicDto> characteristics) {
        this.characteristics = characteristics;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }
    //    public List<Reservation> getReservations() {
//        return reservations;
//    }
//
//
//    public void setReservations(List<ReservationDto> reservationDtos) {
//    }
}
