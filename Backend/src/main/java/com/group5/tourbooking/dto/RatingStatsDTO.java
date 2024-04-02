package com.group5.tourbooking.dto;

public class RatingStatsDTO {
    private double averageRating;
    private int ratingsCount;

    public RatingStatsDTO() {
    }

    public RatingStatsDTO(double averageRating, int ratingsCount) {
        this.averageRating = averageRating;
        this.ratingsCount = ratingsCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }
}
