package com.group5.tourbooking.dto;

import java.time.LocalDate;

public class AvailabilityDTO {
    private LocalDate date;
    private int availableSlots;

    public AvailabilityDTO() {
    }

    public AvailabilityDTO(LocalDate date, int i) {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void setAvailableSlots(int availableSlots) {
        this.availableSlots = availableSlots;
    }
}
