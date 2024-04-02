package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Characteristic;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CharacteristicService {
    Characteristic createCharacteristic(Characteristic characteristic);
    List<Characteristic> findAllCharacteristic();
    Characteristic findCharacteristicById(Long id);
    Characteristic updateCharacteristic(Characteristic characteristic);
    void deleteCharacteristic(Long id);
}
