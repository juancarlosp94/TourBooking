package com.group5.tourbooking.mapper;

import com.group5.tourbooking.dto.CategoryDto;
import com.group5.tourbooking.model.Category;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public class CategoryMapper {
    public CategoryDto categoryToDto(Category category){
        if (category == null) {
            return null;
        }

        CategoryDto dto= new CategoryDto();

        dto.setId(category.getId());
        dto.setName(category.getName());
        dto.setUrlCategoryImage(category.getUrlCategoryImage());
        dto.setDescription(category.getDescription());

        return dto;
    }

    public Category dtoToCategory(CategoryDto categoryDto){
        if (categoryDto == null) {
            return null;
        }

        Category category= new Category();

        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setUrlCategoryImage(categoryDto.getUrlCategoryImage());
        category.setDescription(categoryDto.getDescription());
        return category;
    }
}
