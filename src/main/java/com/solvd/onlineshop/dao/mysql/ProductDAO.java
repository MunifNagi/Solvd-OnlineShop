package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.dao.IProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends MySQLDAO implements IProductDAO {

    private static String readQuery = "SELECT * FROM Product where id=?";
    private static String removeQuery = "DElETE FROM Product WHERE id = ?";
    private static String insertQuery = "INSERT INTO Order VALUES(?,?,?,?,?,?,?,?,?)";
    private static String updateQuery = "UPDATE User SET price = ?, description = ?, category_id = ?, in_stock = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Product";
    private static String readByManuIdQuery = "select * from Product where manufacturer_id = ?";
    private static String readByCategoryIdQuery = "select * from Product where category_id = ?";
    @Override
    public Product getByID(long id){
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                long categoryId = rs.getLong("category_id");
                double weight = rs.getDouble("weights");
                int inStock = rs.getInt("in_stock");
                long discountId = rs.getInt("discount_id");
                long manufacturerId = rs.getInt("manufacturer_id");
                Product product = new Product(id, name, price, description, categoryId, weight, inStock, discountId, manufacturerId);
                return product;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

    @Override
    public void remove(long id){
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(removeQuery)) {
            ps.setLong(1,id);
            if (ps.executeUpdate() > 0) {
                System.out.println("delete is done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Product product) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, product.getProductId());
            ps.setString(2, product.getName());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getDescription());
            ps.setLong(5, product.getCategoryId());
            ps.setDouble(6, product.getWeight());
            ps.setLong(7, product.getInStock());
            ps.setLong(8, product.getDiscountId());
            ps.setLong(9, product.getManufacturerId());
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
    public void update(Product product) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setDouble(1,product.getPrice());
            ps.setString(2,product.getDescription());
            ps.setLong(3,product.getCategoryId());
            ps.setLong(4,product.getInStock());
            ps.setLong(5,product.getProductId());
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
    public List<Product> getAllProduct() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                long categoryId = rs.getLong("category_id");
                double weight = rs.getDouble("weights");
                int inStock = rs.getInt("in_stock");
                long discountId = rs.getInt("discount_id");
                long manufacturerId = rs.getInt("manufacturer_id");
                Product product = new Product(id, name, price, description, categoryId, weight, inStock, discountId, manufacturerId);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return products;
    }

    @Override
    public List<Product> getProductByCategoryId(long categoryId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readByCategoryIdQuery)) {
            ps.setLong(1,categoryId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                double weight = rs.getDouble("weights");
                int inStock = rs.getInt("in_stock");
                long discountId = rs.getInt("discount_id");
                long manufacturerId = rs.getInt("manufacturer_id");
                Product product = new Product(id, name, price, description, categoryId, weight, inStock, discountId, manufacturerId);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return products;
    }

    @Override
    public List<Product> getProductByManufacturerId(long manufacturerId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Product> products = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readByManuIdQuery)) {
            ps.setLong(1,manufacturerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                String description = rs.getString("description");
                long categoryId = rs.getLong("category_id");
                double weight = rs.getDouble("weights");
                int inStock = rs.getInt("in_stock");
                long discountId = rs.getInt("discount_id");
                Product product = new Product(id, name, price, description, categoryId, weight, inStock, discountId, manufacturerId);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return products;
    }
}
