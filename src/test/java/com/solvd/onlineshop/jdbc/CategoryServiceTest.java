package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.services.ICategoryService;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class CategoryServiceTest {
    private ICategoryService categoryService;
    private Category category;

    @BeforeClass
    public void setUp() {
        categoryService = new com.solvd.onlineshop.services.jdbcImp.CategoryService();
        category = new Category(1, "Furniture");
    }

    @Test
    public void testCreateCategory() {
        List<Category> categories = categoryService.getAllCategories();
        int categoriesSize = categories.size();
        categoryService.createCategory(category);
        Assert.assertEquals(categoryService.getAllCategories().size(), categoriesSize + 1);
    }

    @Test(priority = 1)
    public void testGetCategoryById() {
        Category c = categoryService.getCategoryById(1);
        Assert.assertEquals(c.getCategoryId(), 1);
        Assert.assertEquals(c.getName(), "Furniture");
        Assert.assertEquals(c, category);
    }

    @Test(priority = 2)
    public void testUpdateCategory() {
        Category testCategory = categoryService.getCategoryById(1);
        testCategory.setName("Electronics");
        categoryService.updateCategory(testCategory);
        Category c = categoryService.getCategoryById(1);
        Assert.assertEquals(c.getName(), "Electronics");
        Assert.assertEquals(c, testCategory);
    }

    @AfterSuite
    public void destroy() {
        categoryService.removeCategory(1);
    }
}
