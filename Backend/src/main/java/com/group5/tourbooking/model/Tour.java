package com.group5.tourbooking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group5.tourbooking.dto.RatingCommentsDto;
import com.group5.tourbooking.dto.RatingStatsDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "tours")
@Data
@Getter
@Setter
public class Tour {
    private static final Logger logger = LogManager.getLogger(Tour.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 1000)
    private String name;
    @Column(length = 10000)
    private String description;
    @Column(name = "short_description", length = 2000)
    private String shortDescription;
    @Column(precision = 19, scale = 4)
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categories_id")
    @JsonManagedReference
    @JsonIgnore
    private Category category;
    @Column
    private String ubication;
    @ManyToMany
    @JoinTable(
            name = "tour_characteristic",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "characteristic_id")
    )
    private List<Characteristic> characteristics = new ArrayList<>();
    @ManyToMany
    @JoinTable(
            name = "tour_policy",
            joinColumns = @JoinColumn(name = "tour_id"),
            inverseJoinColumns = @JoinColumn(name = "policy_id")
    )
    private List<Policy> policies = new ArrayList<>();
    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Image> images = new ArrayList<>();
    @ManyToMany(mappedBy = "favoriteTours", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
    private List<Usuario> usersWhoFavorited = new ArrayList<>();

    public List<Usuario> getUsersWhoFavorited() {
        return usersWhoFavorited;
    }

    public void setUsersWhoFavorited(List<Usuario> usersWhoFavorited) {
        this.usersWhoFavorited = usersWhoFavorited;
    }

    public void addUserWhoFavorited(Usuario user) {
        usersWhoFavorited.add(user);
        user.getFavoriteTours().add(this);
    }

    public void removeUserWhoFavorited(Usuario user) {
        usersWhoFavorited.remove(user);
        user.getFavoriteTours().remove(this);
    }

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonManagedReference
    @JsonIgnore
    private List<Reservation> reservations = new ArrayList<>();

    @JsonManagedReference
    @JsonIgnore
    @OneToOne(mappedBy = "tour", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Schedule schedule;

    public Tour() {
    }

    public Tour(Long id, String name, String description, String shortDescription, BigDecimal price,
                Category category, List<Characteristic> characteristics, List<Policy> policies, List<Image> images, Schedule schedule, String ubication) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.shortDescription = shortDescription;
        this.price = price;
        this.category = category;
        this.characteristics = characteristics;
        this.policies = policies;
        this.images = images;
        this.schedule = schedule;
        this.ubication = ubication;
    }

    public RatingStatsDTO getRatingStats() {
        RatingStatsDTO ratingStatsDTO=new RatingStatsDTO();
        double average=0.0;
        int totalRatings = 0;
        int ratingsCount = 0;

        for (Reservation reservation : reservations) {
            if (reservation.getRating() != null) {
                totalRatings += reservation.getRating().getRatingValue();
                ratingsCount++;
            }
        }
        average=(double) totalRatings / ratingsCount;
        ratingStatsDTO.setAverageRating(average);
        ratingStatsDTO.setRatingsCount(ratingsCount);

        return ratingStatsDTO;
    }
/*
    public List<RatingCommentsDto> getRatingComments(){
        List<RatingCommentsDto> commentsDtoList= new ArrayList<>();

        for (Reservation reservation : reservations) {
            if (reservation.getRating() != null) {
                RatingCommentsDto ratingCommentsDto= new RatingCommentsDto();
                Long idUsuario= reservation.getRating().getUsuarioId();

                ratingCommentsDto.setUsuarioId(idUsuario);  // Corrección aquí
                logger.info("ID USUARIO DEL COMMENT: "+ idUsuario);

                ratingCommentsDto.setTourID(reservation.getTour().getId());
                ratingCommentsDto.setComment(reservation.getRating().getComments());

                commentsDtoList.add(ratingCommentsDto);
                logger.info("DTO: "+ ratingCommentsDto);
            }
        }
        return commentsDtoList;
    }
*/

    public void addImage(Image image){
        images.add(image);
        image.setTour(this);
    }

    public Long getId() {
        return id;
    }

    public List<Characteristic> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<Characteristic> characteristics) {
        this.characteristics = characteristics;
    }

    public Category getCategory() {

        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
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

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getUbication() {
        return ubication;
    }

    public void setUbication(String ubication) {
        this.ubication = ubication;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }
}
