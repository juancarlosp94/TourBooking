package com.group5.tourbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group5.tourbooking.aws.ImageUploadService;
import com.group5.tourbooking.dto.RatingCommentsDto;
import com.group5.tourbooking.dto.RatingStatsDTO;
import com.group5.tourbooking.dto.TourDto;
import com.group5.tourbooking.mapper.CharacteristicMapper;
import com.group5.tourbooking.mapper.TourMapper;
import com.group5.tourbooking.model.Characteristic;
import com.group5.tourbooking.model.Tour;
import com.group5.tourbooking.model.Image;
import com.group5.tourbooking.service.TourService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/tours")
@CrossOrigin(origins = "*")
public class TourController {

    @Autowired
    TourService tourService;
    @Autowired
    TourMapper tourMapper;
    @Autowired
    ImageUploadService imageUploadService;
    @Autowired
    CharacteristicMapper characteristicMapper;


//create 1 exception name:

    @PostMapping(consumes = "multipart/form-data")
    @Transactional //(descomentar para que si rebota las images, no cree el tour. hace ambas o nada)
    public ResponseEntity<?> createTour(
            @RequestParam("tour") String tourJson,
            @RequestParam("images") MultipartFile[] images)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        TourDto tourDto = objectMapper.readValue(tourJson, TourDto.class);

        Tour tour = new Tour();
        tour.setName(tourDto.getName());
        tour.setDescription(tourDto.getDescription());
        tour.setShortDescription(tourDto.getShortDescription());
        tour.setPrice(tourDto.getPrice());
        tour.setCategory(tourMapper.categoryDtoToCategory(tourDto.getCategory()));
        tour.setUbication(tourDto.getUbication());
        List<Characteristic> characteristics = tourDto.getCharacteristics().stream()
                .map(characteristicMapper::dtoToCharacteristic)
                .collect(Collectors.toList());
        tour.setCharacteristics(characteristics);
        // Primero guardamos el tour sin las imágenes
        Tour createdTour = tourService.createTour(tour);
        //ESTO ESTA BIEN, SE TIENE QUE RESOLVER LO DE AWS S3 PARA USARLO
        // Lógica para subir las imágenes a S3 y guardar las URLs en la base de datos

        for (MultipartFile file : images) {
            String imageUrl = imageUploadService.uploadImage(file);
            Image image = new Image();
            image.setUrl(imageUrl);
            image.setTour(createdTour);
            createdTour.addImage(image);
        }
        //HARCODEO IMAGEN HASTA QUE SE RESUELVA LO DE AWS S3 BUCKET
        //Image image = new Image();
        //image.setUrl("https://imagizer.imageshack.com/img924/4403/E3N3FV.jpg");
        //createdTour.addImage(image);


        tourService.updateTour(createdTour); // Esto guardará las imágenes junto con el tour

        return ResponseEntity.ok(tourMapper.tourToDto(createdTour));

    }


    //read all
    @GetMapping
    public ResponseEntity<List<TourDto>> getAllTour() {
        List<Tour> tours = tourService.findAllTours();


        List<TourDto> tourDtos = tours.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }

    //read by id
    @GetMapping("/{id}")
    public ResponseEntity<TourDto> findTourById(@PathVariable("id") Long id){
        TourDto tourDto= new TourDto();
        Tour tour= tourService.findTourById(id);
        tourDto= tourMapper.tourToDto(tour);

        return new ResponseEntity<>(tourDto, HttpStatus.OK);
    }

    //read all by ids
    @GetMapping("/findbyids")
    public List<TourDto> findToursByIds(@RequestParam List<Long> ids){
        List<Tour> tourList= tourService.findToursByIds(ids);
        List<TourDto> tourDtoList= tourList.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());
        return tourDtoList;
    }

    //update
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity<TourDto> updateTour(
            @PathVariable Long id,
            @RequestParam("tour") String tourJson,
            @RequestParam(value = "images", required = false) MultipartFile[] images) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TourDto tourDto = objectMapper.readValue(tourJson, TourDto.class);
        System.out.println(id);
        Tour existingTour = tourService.findTourById(id);

        if(existingTour == null) {
            throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
        }
        // 1. Extraer los nombres de archivo de las URLs antiguas
        List<String> oldFilenames = existingTour.getImages().stream()
                .map(image -> imageUploadService.extractFilenameFromUrl(image.getUrl()))
                .collect(Collectors.toList());

        if (tourDto.getName() != null && !tourDto.getName().trim().isEmpty()) {
            existingTour.setName(tourDto.getName());
        }
        if (tourDto.getDescription() != null && !tourDto.getDescription().trim().isEmpty()) {
            existingTour.setDescription(tourDto.getDescription());
        }
        if (tourDto.getShortDescription() != null && !tourDto.getShortDescription().trim().isEmpty()) {
            existingTour.setShortDescription(tourDto.getShortDescription());
        }
        if (tourDto.getPrice() != null ) {
            existingTour.setPrice(tourDto.getPrice());
        }
        if (tourDto.getCategory() != null) {

            existingTour.setCategory(tourMapper.categoryDtoToCategory(tourDto.getCategory()));
        }
        if (tourDto.getUbication() != null){
            existingTour.setUbication(tourDto.getUbication());
        }
        if(tourDto.getCharacteristics() != null && !tourDto.getCharacteristics().isEmpty()) {
            List<Characteristic> characteristics = tourDto.getCharacteristics().stream()
                    .map(characteristicMapper::dtoToCharacteristic)
                    .collect(Collectors.toList());
            existingTour.setCharacteristics(characteristics);
        }
        // 2. Eliminar las imágenes antiguas y subir las nuevas
        List<Image> updatedImages = new ArrayList<>();
        for (MultipartFile file : images) {
            String newImageUrl = imageUploadService.uploadImage(file);
            Image updatedImage = new Image();
            updatedImage.setUrl(newImageUrl);
            updatedImage.setTour(existingTour);
            existingTour.getImages().add(updatedImage);
        }

        // Lógica para eliminar las imágenes antiguas de S3
        for (String oldFilename : oldFilenames) {
            imageUploadService.deleteImage(oldFilename);
        }


        Tour updatedTour = tourService.updateTour(existingTour);
        TourDto responseDto = tourMapper.tourToDto(updatedTour);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping(value = "/{id}", consumes = "multipart/form-data")
    @Transactional
    public ResponseEntity<TourDto> update(
            @PathVariable Long id,
            @RequestParam("tour") String tourJson,
            @RequestParam(value = "images", required = false) MultipartFile[] images) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TourDto tourDto = objectMapper.readValue(tourJson, TourDto.class);
        System.out.println(id);
        Tour existingTour = tourService.findTourById(id);

        if(existingTour == null) {
            throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
        }
        // 1. Extraer los nombres de archivo de las URLs antiguas
        List<String> oldFilenames = existingTour.getImages().stream()
                .map(image -> imageUploadService.extractFilenameFromUrl(image.getUrl()))
                .collect(Collectors.toList());
        if (tourDto.getName() != null && !tourDto.getName().trim().isEmpty()) {
            existingTour.setName(tourDto.getName());
        }
        if (tourDto.getDescription() != null && !tourDto.getDescription().trim().isEmpty()) {
            existingTour.setDescription(tourDto.getDescription());
        }
        if (tourDto.getShortDescription() != null && !tourDto.getShortDescription().trim().isEmpty()) {
            existingTour.setShortDescription(tourDto.getShortDescription());
        }
        if (tourDto.getPrice() != null ) {
            existingTour.setPrice(tourDto.getPrice());
        }
        if (tourDto.getCategory() != null) {

            existingTour.setCategory(tourMapper.categoryDtoToCategory(tourDto.getCategory()));
        }
        if (tourDto.getUbication() != null){
            existingTour.setUbication(tourDto.getUbication());
        }
        if(tourDto.getCharacteristics() != null && !tourDto.getCharacteristics().isEmpty()) {
            List<Characteristic> characteristics = tourDto.getCharacteristics().stream()
                    .map(characteristicMapper::dtoToCharacteristic)
                    .collect(Collectors.toList());
            existingTour.setCharacteristics(characteristics);
        }
        // 2. Eliminar las imágenes antiguas y subir las nuevas
        if(images != null){
        List<Image> updatedImages = new ArrayList<>();
        for (MultipartFile file : images) {
            String newImageUrl = imageUploadService.uploadImage(file);
            Image updatedImage = new Image();
            updatedImage.setUrl(newImageUrl);
            updatedImage.setTour(existingTour);
            existingTour.getImages().add(updatedImage);
        }

        // Lógica para eliminar las imágenes antiguas de S3
        for (String oldFilename : oldFilenames) {
            imageUploadService.deleteImage(oldFilename);
        }
        }


        Tour updatedTour = tourService.updateTour(existingTour);
        TourDto responseDto = tourMapper.tourToDto(updatedTour);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        Tour existingTour = tourService.findTourById(id);
        if(existingTour == null) {
            throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
        }
        //BORRAR LAS IMAGENES DEL BUCKET
        // 1. Extraer los nombres de archivo de las URLs antiguas
        List<String> oldFilenames = existingTour.getImages().stream()
                .map(image -> imageUploadService.extractFilenameFromUrl(image.getUrl()))
                .collect(Collectors.toList());
        // Lógica para eliminar las imágenes antiguas de S3
        for (String oldFilename : oldFilenames) {
            imageUploadService.deleteImage(oldFilename);
        }
        // Eliminamos el tour
        tourService.deleteTour(id);

        // Respondemos con un estado HTTP 204 No Content (es común usar este estado para indicar que el recurso fue eliminado con éxito)
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/toursbycategory/{id}")
    public ResponseEntity<List<TourDto>> getToursByCategory(@PathVariable Long id){
        List<Tour> tours= tourService.findToursByCategoryId(id);

        List<TourDto> tourDtos = tours.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(tourDtos, HttpStatus.OK);
    }

    @GetMapping("/search/byname")
    public ResponseEntity<List<TourDto>> searchTourByname(@RequestParam String name){
        List<Tour> tourList=  tourService.findByNameContainingIgnoreCase(name);
        List<TourDto> dtoList= tourList.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());

            return ResponseEntity.ok()
                    .body(dtoList);
    }

    @GetMapping("/suggest/byname")
    public ResponseEntity<List<TourDto>> suggestTourByname(@RequestParam String name){
        List<Tour> tourList=  tourService.findTop5ByNameContainingIgnoreCase(name);
        List<TourDto> dtoList= tourList.stream()
                .map(tourMapper::tourToDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(dtoList);
    }

    @GetMapping("/ratingstats/{tourId}")
    public ResponseEntity<RatingStatsDTO> getRatingStats(@PathVariable Long tourId) {
        try {
            RatingStatsDTO ratingStats = tourService.getRatingStats(tourId);
            return ResponseEntity.ok(ratingStats);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ratingcomments/{tourId}")
    public ResponseEntity<List<RatingCommentsDto>> getRatingComments(@PathVariable Long tourId) {
        try {
            List<RatingCommentsDto> ratingCommentsDto = tourService.getRatingComments(tourId);

            return ResponseEntity.ok(ratingCommentsDto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

