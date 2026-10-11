package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService{

    private Long categoryNextId = 1L;
    private List<Category> categories = new ArrayList<>();

    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
       category.setCategoryId(categoryNextId++);
        categories.add(category);

    }

    @Override
    public String deleteCategory(Long id) {

        Category category = categories.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));

            categories.remove(category);

        return "Category deleted successfully";
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        Optional<Category> categoryToUpdate = categories.stream()
                .filter(c -> c.getCategoryId().equals(id))
                .findFirst();

        if (categoryToUpdate.isPresent()) {
            Category existingCategory = categoryToUpdate.get();
            existingCategory.setCategoryName(category.getCategoryName());
            return existingCategory;
        } else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
         }
        }
    }
