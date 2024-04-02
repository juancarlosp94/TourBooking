package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Image;

import java.util.List;

public interface ImageService {
    Image createImage(Image image);
    List<Image> findAllImages();
    Image findImageById(Long id);
    Image updateImage(Image image);
    void deleteImage(Long id);
}
