package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.ICategoryDAO;
import com.solvd.onlineshop.dao.mysql.CategoryDAO;
import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.services.ICategoryService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CategoryService implements ICategoryService {
    private ICategoryDAO categoryDAO = new CategoryDAO();
    private static final Logger logger = LogManager.getLogger(CategoryService.class);

    @Override
    public Category getCategoryById(long id) {
        Category category = categoryDAO.getByID(id);
        if (category == null) {
            logger.error("category with id " + id + " wasn't found!");
        }
        return category;
    }

    @Override
    public void createCategory(Category category) {
        this.categoryDAO.create(category);
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryDAO.update(category);
    }

    @Override
    public void removeCategory(long id) {
        this.categoryDAO.remove(id);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = null;
        categories = categoryDAO.getAllCategories();
        return categories;
    }
}
