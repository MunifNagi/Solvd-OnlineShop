package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.services.IProductService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ProductServiceTest {
    private IProductService productService;
    private Product product;

    @BeforeClass
    public void setUp() {
        productService = new com.solvd.onlineshop.services.jdbcImp.ProductService();
        product = new Product(1, "computer", 1599.99, null, 1, 4.3, 50, 1, 1);
    }

    @Test
    public void testCreateProduct() {
        List<Product> products = productService.getAllProducts();
        int numProducts = products.size();
        productService.createProduct(product);
        Assert.assertEquals(productService.getAllProducts().size(), numProducts + 1);
    }

    @Test(priority = 1)
    public void testGetProductById() {
        Product p = productService.getProductByID(1);
        Assert.assertEquals(p.getName(), "computer");
        Assert.assertEquals(p.getPrice(), 1599.99);
        Assert.assertEquals(p.getWeight(), 4.3);
        Assert.assertEquals(p.getInStock(), 50);
        Assert.assertEquals(p, product);
    }

    @Test(priority = 2)
    public void testUpdateProduct() {
        Product testProduct = productService.getProductByID(1);
        testProduct.setPrice(1799.99);
        productService.updateProduct(testProduct);
        Product p = productService.getProductByID(1);
        Assert.assertEquals(p.getPrice(), 1799.99);
        Assert.assertEquals(p, testProduct);
    }

    @AfterClass
    public void destroy() {
        productService.removeProduct(1);
    }
}
