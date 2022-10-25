package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Category;

import java.util.List;

public interface ICategoryService {
    Category getCategoryById(long id);

    void createCategory(Category category);

    void updateCategory(Category category);

    void removeCategory(long id);

    List<Category> getAllCategories();
}
