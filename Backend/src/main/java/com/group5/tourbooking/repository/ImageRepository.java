package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
