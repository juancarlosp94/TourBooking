package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Characteristic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CharacteristicRepository extends JpaRepository<Characteristic, Long> {
    Optional<Characteristic> findByName(String name);
}
