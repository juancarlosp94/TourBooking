package com.group5.tourbooking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characteristic")
public class Characteristic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String urlCharacteristicImage;

    @ManyToMany
    @JoinTable(
            name = "tour_characteristic",
            joinColumns = @JoinColumn(name = "characteristic_id"),
            inverseJoinColumns = @JoinColumn(name = "tour_id")
    )
    @JsonManagedReference
    @JsonIgnore
    private List<Tour> tours = new ArrayList<>();

    // Constructor, getters, setters, etc...


    public Characteristic(Long id, String name, String urlCharacteristicImage) {
        this.id = id;
        this.name = name;
        this.urlCharacteristicImage = urlCharacteristicImage;
    }

    public Characteristic() {

    }

    public String getUrlCharacteristicImage() {
        return urlCharacteristicImage;
    }

    public void setUrlCharacteristicImage(String urlCharacteristicImage) {
        this.urlCharacteristicImage = urlCharacteristicImage;
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

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}

