package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.services.ICategoryService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class CategoryServiceTest {
    private ICategoryService categoryService;
    private Category category;

    @BeforeClass
    public void setUp() {
        categoryService = new com.solvd.onlineshop.services.jdbcImp.CategoryService();
    }

    @BeforeMethod
    public void createCategory() {
        category = new Category(2, "Furniture");
    }

    @Test
    public void testCreateCategory() {
        List<Category> categories = categoryService.getAllCategories();
        int categoriesSize = categories.size();
        categoryService.createCategory(category);
        Assert.assertEquals(categoryService.getAllCategories().size(), categoriesSize + 1, "Number of categories should have been incremented after insertion");
    }

    @Test
    public void testGetCategoryById() {
        Category c = categoryService.getCategoryById(2);
        Assert.assertEquals(c.getCategoryId(), 2, "Category id didn't match");
        Assert.assertEquals(c.getName(), "Furniture", "Category name didn't match");
        Assert.assertEquals(c, category, "Category selected from database didn't match with expected Category");
    }

    @Test
    public void testUpdateCategory() {
        Category testCategory = categoryService.getCategoryById(2);
        testCategory.setName("Electronics");
        categoryService.updateCategory(testCategory);
        Category c = categoryService.getCategoryById(2);
        Assert.assertEquals(c.getName(), "Electronics", "Category name was expected to be updated");
        Assert.assertEquals(c, testCategory, "Category was expected to be updated");
    }

    @AfterClass
    public void destroy() {
        categoryService.removeCategory(2);
    }
}
