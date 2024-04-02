package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Image;
import com.group5.tourbooking.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService{
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image createImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public List<Image> findAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public Image findImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    @Override
    public Image updateImage(Image image) {
        return imageRepository.save(image);
    }

    @Override
    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
