package com.group5.tourbooking.dto;

public class RatingCommentsDto {
    private Long tourID;
    private String userName;
    private Long usuarioId;
    private String comment;

    public RatingCommentsDto() {
    }

    public RatingCommentsDto(Long tourID, String userName, Long usuarioId, String comment) {
        this.tourID = tourID;
        this.userName = userName;
        this.usuarioId = usuarioId;
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getTourID() {
        return tourID;
    }

    public void setTourID(Long tourID) {
        this.tourID = tourID;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "RatingCommentsDto{" +
                "tourID=" + tourID +
                ", usuarioId=" + usuarioId +
                ", comment='" + comment + '\'' +
                '}';
    }
}
