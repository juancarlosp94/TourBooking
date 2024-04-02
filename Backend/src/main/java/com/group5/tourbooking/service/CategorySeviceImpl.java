package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Category;
import com.group5.tourbooking.repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class CategorySeviceImpl implements  CategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }


    public Category findCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Can´t find Category with ID: "+id));
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
