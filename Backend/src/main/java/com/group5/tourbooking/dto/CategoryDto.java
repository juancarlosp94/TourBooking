package com.group5.tourbooking.dto;

public class CategoryDto {
    private Long id;
    private String name;
    private String description;
    private String urlCategoryImage;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getUrlCategoryImage() {
        return urlCategoryImage;
    }

    public void setUrlCategoryImage(String urlCategoryImage) {
        this.urlCategoryImage = urlCategoryImage;
    }
}
