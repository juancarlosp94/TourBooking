package com.group5.tourbooking.repository;

import com.group5.tourbooking.model.Characteristic;
import com.group5.tourbooking.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy,Long> {
    Optional<Policy> findByName(String name);

}
