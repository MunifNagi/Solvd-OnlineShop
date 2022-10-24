package com.solvd.onlineshop.myBatis;

import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.services.IProductService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ProductServiceTest {
    private IProductService productService;
    private Product product;

    @BeforeClass
    public void setUp() {
        productService = new com.solvd.onlineshop.services.myBatisImp.ProductService();
    }

    @BeforeMethod
    public void createProduct() {
        product = new Product(1, "computer", 1599.99, null, 1, 4.3, 50, 1, 1);
    }

    @Test
    public void testCreateProduct() {
        List<Product> products = productService.getAllProducts();
        int numProducts = products.size();
        productService.createProduct(product);
        Assert.assertEquals(productService.getAllProducts().size(), numProducts + 1, "Number of products should have been incremented after insertion");
    }

    @Test
    public void testGetProductById() {
        Product p = productService.getProductByID(1);
        Assert.assertEquals(p.getName(), "computer", "Name of product didn't match");
        Assert.assertEquals(p.getPrice(), 1599.99, "Price of product didn't match");
        Assert.assertEquals(p.getWeight(), 4.3, "Weight of product didn't match");
        Assert.assertEquals(p.getInStock(), 50, "INStock quantity product didn't match");
        Assert.assertEquals(p, product, "product selected from database didn't match with expected product");
    }

    @Test
    public void testUpdateProduct() {
        Product testProduct = productService.getProductByID(1);
        testProduct.setPrice(1799.99);
        productService.updateProduct(testProduct);
        Product p = productService.getProductByID(1);
        Assert.assertEquals(p.getPrice(), 1799.99, "Product price was expected to be updated");
        Assert.assertEquals(p, testProduct, "Product was expected to be updated");
    }

    @AfterClass
    public void destroy() {
        productService.removeProduct(1);
    }
}
