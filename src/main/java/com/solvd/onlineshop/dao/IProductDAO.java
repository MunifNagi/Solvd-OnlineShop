package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Product;

import java.util.List;

public interface IProductDAO extends IBaseDAO<Product> {
    List<Product> getAllProduct();
    List<Product> getProductByCategoryID(long categoryId);
}
