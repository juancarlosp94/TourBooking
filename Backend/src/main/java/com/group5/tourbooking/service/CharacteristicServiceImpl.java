package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Characteristic;
import com.group5.tourbooking.repository.CharacteristicRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CharacteristicServiceImpl implements CharacteristicService {
    @Autowired
    CharacteristicRepository characteristicRepository;
    @Override
    public Characteristic createCharacteristic(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public List<Characteristic> findAllCharacteristic() {
        return characteristicRepository.findAll();
    }

    @Override
    public Characteristic findCharacteristicById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("The given id must not be null");
        }
        return characteristicRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Can´t find Characteristic with ID: " + id));
    }


    @Override
    public Characteristic updateCharacteristic(Characteristic characteristic) {
        return characteristicRepository.save(characteristic);
    }

    @Override
    public void deleteCharacteristic(Long id) {
        characteristicRepository.deleteById(id);

    }
}
