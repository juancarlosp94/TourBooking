package com.group5.tourbooking.dto;

import com.group5.tourbooking.model.Tour;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;

public class ScheduleDto {
    private Tour tour;
    private Set<DayOfWeek> daysOfWeek;
    private int maxAvailability;

    public ScheduleDto() {
    }

    public ScheduleDto(Tour tour, Set<DayOfWeek> daysOfWeek, int maxAvailability) {
        this.tour = tour;
        this.daysOfWeek = daysOfWeek;
        this.maxAvailability = maxAvailability;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public Set<DayOfWeek> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(Set<DayOfWeek> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    public int getMaxAvailability() {
        return maxAvailability;
    }

    public void setMaxAvailability(int maxAvailability) {
        this.maxAvailability = maxAvailability;
    }
}
