package com.group5.tourbooking.dto;

public class CharacteristicDto {
    private Long id;
    private String name;
    private String urlCharacteristicImage;

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
}
