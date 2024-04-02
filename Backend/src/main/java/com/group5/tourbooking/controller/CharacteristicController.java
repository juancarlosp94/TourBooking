package com.group5.tourbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group5.tourbooking.aws.ImageUploadService;

import com.group5.tourbooking.dto.CharacteristicDto;
import com.group5.tourbooking.mapper.CharacteristicMapper;
import com.group5.tourbooking.model.Characteristic;
import com.group5.tourbooking.service.CharacteristicService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
@Controller
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/characteristic")
public class CharacteristicController {
    @Autowired
    CharacteristicService characteristicService;
    @Autowired
    CharacteristicMapper characteristicMapper;

    //CREATE
    @PostMapping(consumes = "multipart/form-data")
    ResponseEntity<?> createCharacteristic(
            @RequestParam("characteristic") String characteristicJson) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CharacteristicDto characteristicDto = objectMapper.readValue(characteristicJson, CharacteristicDto.class);

        Characteristic characteristicCreated= new Characteristic();
        characteristicCreated =characteristicMapper.dtoToCharacteristic(characteristicDto);
        //ESTO ESTA BIEN, SE TIENE QUE RESOLVER LO DE AWS S3 PARA USARLO


        characteristicService.createCharacteristic(characteristicCreated);

        return ResponseEntity.ok(characteristicMapper.characteristicToDto(characteristicCreated));
    }
    //READ
    @GetMapping
    public ResponseEntity<List<CharacteristicDto>> getAllCharacteristic(){
        List<Characteristic> categories= characteristicService.findAllCharacteristic();

        List<CharacteristicDto> characteristicDtos= categories.stream()
                .map(characteristicMapper::characteristicToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(characteristicDtos);
    }
    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CharacteristicDto> getCharacteristicById(
            @PathVariable Long id){
        Characteristic characteristic= characteristicService.findCharacteristicById(id);
        CharacteristicDto characteristicDto= characteristicMapper.characteristicToDto(characteristic);

        return new ResponseEntity<>(characteristicDto, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CharacteristicDto> updateCharacteristic(
            @RequestParam("characteristic") String characteristicJson)  throws JsonProcessingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CharacteristicDto characteristicDto = objectMapper.readValue(characteristicJson, CharacteristicDto.class);

        Characteristic existingCharacteristic = characteristicService.findCharacteristicById(characteristicDto.getId());

        if (existingCharacteristic == null) {
            throw new EntityNotFoundException("No se encontró una Caracteristica con el ID especificado");
        }

        if (characteristicDto.getName() != null && !characteristicDto.getName().trim().isEmpty()) {
            existingCharacteristic.setName(characteristicDto.getName());
        }

        if (characteristicDto.getUrlCharacteristicImage() != null && !characteristicDto.getUrlCharacteristicImage().isEmpty()) {
            // If there's an old image, delete it
            existingCharacteristic.setUrlCharacteristicImage(characteristicDto.getUrlCharacteristicImage());
        }

        Characteristic updatedCharacteristic = characteristicService.updateCharacteristic(existingCharacteristic);
        CharacteristicDto responseDto = characteristicMapper.characteristicToDto(updatedCharacteristic);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }


    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCharacteristic(
            @PathVariable Long id
    ){
        Characteristic existingCharacteristic= characteristicService.findCharacteristicById(id);
        if(existingCharacteristic == null) {
            throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
        }
        characteristicService.deleteCharacteristic(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
