package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.ICategoryDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends MySQLDAO implements ICategoryDAO {

    @Override
    public Category getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement("select * from Category where id=?")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                Category category = new Category(id, name);
                return category;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

    @Override
    public void remove(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "DElETE FROM Category WHERE id = ?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ps.setLong(1,id);
            if (ps.executeUpdate()>0) {
                System.out.println("delete is done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Category category) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "INSERT INTO Category VALUES(?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, category.getCategoryId());
            ps.setString(2, category.getName());
            ps.executeUpdate();
            System.out.println("Insert Query Executed");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Category category) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "UPDATE User SET name = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1,category.getName());
            ps.setLong(2,category.getCategoryId());
            if (ps.executeUpdate() > 0) {
                System.out.println("Update is done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }


    @Override
    public List<Category> getAllCategories() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Category> categories = new ArrayList<>();
        String query = "select * from Category";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Category category = new Category(id, name);
                categories.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return categories;
    }
}
