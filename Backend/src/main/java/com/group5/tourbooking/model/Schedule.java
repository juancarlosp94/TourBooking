package com.group5.tourbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.util.Set;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "tour_id")
    @JsonManagedReference
    @JsonIgnore
    private Tour tour;
    @ElementCollection(targetClass= DayOfWeek.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="schedule_days")
    private Set<DayOfWeek> daysOfWeek;
    @Column(name = "max_availability")
    private int maxAvailability;

    public Schedule(Long id, Tour tour, Set<DayOfWeek> daysOfWeek, int maxAvailability) {
        this.id = id;
        this.tour = tour;
        this.daysOfWeek = daysOfWeek;
        this.maxAvailability = maxAvailability;
    }

    public Schedule() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", tour=" + tour +
                ", daysOfWeek=" + daysOfWeek +
                ", maxAvailability=" + maxAvailability +
                '}';
    }
}