package com.group5.tourbooking.controller;

import com.group5.tourbooking.dto.RatingDto;
import com.group5.tourbooking.dto.RatingDtoId;
import com.group5.tourbooking.model.Rating;
import com.group5.tourbooking.service.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/rating")
public class RatingController {
    @Autowired
    RatingService ratingService;

    //CREATE
    @PostMapping
    public RatingDto createRating(@Valid @RequestBody RatingDtoId ratingDtoId){

        return ratingService.createRatingWithId(ratingDtoId);
    }

    //READ
    @GetMapping
    public List<RatingDto> findAllRating(){
        return ratingService.findAllRating();
    }
    //READ BY ID
    @GetMapping("/{id}")
    public RatingDto findRatingById(@PathVariable Long id){
        return ratingService.findRatingById(id);
    }
    //UPDATE
    @PutMapping
    public RatingDto updateRating(@RequestBody Rating rating){
        return ratingService.updateRating(rating);
    }

    //DELETE
    @DeleteMapping("/{id}")
    public void deleteRatingById(@PathVariable Long id){
        ratingService.deleteRatingById(id);
    }
}
