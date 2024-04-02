package com.group5.tourbooking.dto;

import com.group5.tourbooking.model.Tour;

import java.time.DayOfWeek;
import java.util.Set;

public class ScheduleDtoId {
    private Long tourId;
    private Set<DayOfWeek> daysOfWeek;
    private int maxAvailability;

    public Long getTourId() {
        return tourId;
    }

    public void setTourId(Long tourId) {
        this.tourId = tourId;
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
