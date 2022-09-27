package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Product;

import java.util.List;

public interface IProductService {
    Product getProductByID (long id);
    List<Product> getAllProducts();
    void removeProduct(long id);
    void createProduct(Product product);
    void updateProduct(Product product);

    List<Product> getProductByCategoryId (long categoryID);

    List<Product> getProductByManufacturerId(long manufacturerId);
}
