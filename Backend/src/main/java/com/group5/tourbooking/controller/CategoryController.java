package com.group5.tourbooking.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group5.tourbooking.aws.ImageUploadService;
import com.group5.tourbooking.dto.CategoryDto;
import com.group5.tourbooking.mapper.CategoryMapper;
import com.group5.tourbooking.model.Category;
import com.group5.tourbooking.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {
    //AUTOWIRED
    @Autowired
    CategoryService categoryService;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    ImageUploadService imageUploadService;

    //CREATE
    @PostMapping(consumes = "multipart/form-data")
    ResponseEntity<?> createCategory(
            @RequestParam("category") String categoryJson,
            @RequestParam("urlCategoryImage")MultipartFile urlCategoryImage) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CategoryDto categoryDto = objectMapper.readValue(categoryJson, CategoryDto.class);

        Category categoryCreated= new Category();
        categoryCreated =categoryMapper.dtoToCategory(categoryDto);
        //ESTO ESTA BIEN, SE TIENE QUE RESOLVER LO DE AWS S3 PARA USARLO
        String url= imageUploadService.uploadImage(urlCategoryImage);
        System.out.println(url);
        //HARCODEO IMAGEN HASTA QUE SE RESUELVA LO DE AWS S3 BUCKET
        //String url= "https://imagizer.imageshack.com/img922/381/ohFrme.jpg";
        categoryCreated.setUrlCategoryImage(url);

        categoryService.createCategory(categoryCreated);

        return ResponseEntity.ok(categoryMapper.categoryToDto(categoryCreated));
    }
    //READ
    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
    List<Category> categories= categoryService.findAllCategories();

    List<CategoryDto> categoryDtos= categories.stream()
            .map(categoryMapper::categoryToDto)
            .collect(Collectors.toList());

        return ResponseEntity.ok(categoryDtos);
    }
    //READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(
            @PathVariable Long id){
        Category category= categoryService.findCategoryById(id);
        CategoryDto categoryDto= categoryMapper.categoryToDto(category);

        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    //UPDATE
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestParam("category") String categoryJson,
            @RequestParam(value = "urlCategoryImage", required = false) MultipartFile urlImageCategory)
            throws JsonProcessingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        CategoryDto categoryDto = objectMapper.readValue(categoryJson, CategoryDto.class);

        Category existingCategory = categoryService.findCategoryById(categoryDto.getId());

        if (existingCategory == null) {
            throw new EntityNotFoundException("No se encontró una Categoria con el ID especificado");
        }

        if (categoryDto.getName() != null && !categoryDto.getName().trim().isEmpty()) {
            existingCategory.setName(categoryDto.getName());
        }

        if (categoryDto.getDescription() != null && !categoryDto.getDescription().trim().isEmpty()) {
            existingCategory.setDescription(categoryDto.getDescription());
        }

        if (urlImageCategory != null && !urlImageCategory.isEmpty()) {
            // If there's an old image, delete it
            if (existingCategory.getUrlCategoryImage() != null) {
                imageUploadService.deleteImage(existingCategory.getUrlCategoryImage());
            }

            // Upload the new image and set its URL to the existingCategory
            String newImageUrl = imageUploadService.uploadImage(urlImageCategory);
            existingCategory.setUrlCategoryImage(newImageUrl);
        }

        Category updatedCategory = categoryService.updateCategory(existingCategory);
        CategoryDto responseDto = categoryMapper.categoryToDto(updatedCategory);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }



    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @PathVariable Long id
    ){
        Category existingCategory= categoryService.findCategoryById(id);
        if(existingCategory == null) {
            throw new EntityNotFoundException("No se encontró un tour con el ID especificado");
        }
        String oldUrlImage= existingCategory.getUrlCategoryImage();
        imageUploadService.deleteImage(oldUrlImage);
        categoryService.deleteCategory(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
    