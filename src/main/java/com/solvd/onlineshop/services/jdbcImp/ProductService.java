package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IOrderDAO;
import com.solvd.onlineshop.dao.IProductDAO;
import com.solvd.onlineshop.dao.mysql.OrderDAO;
import com.solvd.onlineshop.dao.mysql.ProductDAO;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.services.IProductService;

import java.util.List;

public class ProductService implements IProductService {
    private IProductDAO productDAO = new ProductDAO();

    @Override
    public Product getProductByID(long id) {
        return productDAO.getByID(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDAO.getAllProduct();
    }

    @Override
    public void removeProduct(long id) {
        productDAO.remove(id);
    }

    @Override
    public void createProduct(Product product) {
        productDAO.create(product);
    }

    @Override
    public void updateProduct(Product product) {
        productDAO.update(product);
    }
}
