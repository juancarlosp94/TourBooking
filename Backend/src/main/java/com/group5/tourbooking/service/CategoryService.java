package com.group5.tourbooking.service;

import com.group5.tourbooking.model.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CategoryService {
    Category createCategory(Category category);
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    Category updateCategory(Category category);
    void deleteCategory(Long id);
}
