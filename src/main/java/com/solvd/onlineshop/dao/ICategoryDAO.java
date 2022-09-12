package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Category;

import java.util.List;

public interface ICategoryDAO extends IBaseDAO<Category>{
    List<Category> getAllCategories();
}
