package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.ICategoryDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends MySQLDAO implements ICategoryDAO {
    private static final Logger logger = LogManager.getLogger(CategoryDAO.class);
    private static String readQuery = "Select * FROM Category  WHERE id = ?";
    private static String removeQuery = "DElETE FROM Category WHERE id = ?";
    private static String insertQuery = "INSERT INTO Category VALUES(?,?)";
    private static String updateQuery = "UPDATE Category SET name = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Category";

    @Override
    public Category getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                Category category = new Category(id, name);
                return category;
            }
        } catch (SQLException e) {
            String message = String.format("Getting category with ID:%d wasn't successful", id);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

    @Override
    public void remove(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(removeQuery)) {
            ps.setLong(1,id);
            if (ps.executeUpdate()>0) {
                String message = String.format("Category with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Category with ID: %d was not removed successfully", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Category category) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, category.getCategoryId());
            ps.setString(2, category.getName());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Category Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Category category) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,category.getName());
            ps.setLong(2,category.getCategoryId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Category with ID: %d was updated successfully",category.getCategoryId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Category with ID: %d was not updated successfully",category.getCategoryId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }


    @Override
    public List<Category> getAllCategories() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Category> categories = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            logger.error("Failed getting all categories records",e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return categories;
    }
}
