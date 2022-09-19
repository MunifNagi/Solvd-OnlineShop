package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.ICartDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Cart;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CartDAO extends MySQLDAO implements ICartDAO {
    private static final Logger logger = LogManager.getLogger(CartDAO.class);
    private static String readQuery = "SELECT * FROM Cart WHERE id = ?";
    private static String removeQuery = "DElETE FROM Cart WHERE id = ?";
    private static String insertQuery = "INSERT INTO Cart VALUES(?,?,?,?,?,?)";
    private static String updateQuery = "UPDATE Cart SET quantity = ? WHERE id = ? AND product_id = ?";
    @Override
    public Cart getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                long orderId = rs.getLong("order_id");
                long productId = rs.getLong("product_id");
                long quantity = rs.getLong("quantity");
                double price = rs.getDouble("price");
                double total = rs.getDouble("total");
                Cart cart = new Cart(id, orderId, productId, quantity, price, total);
                return cart;
            }
        } catch (SQLException e) {
            String message = String.format("Getting Cart with ID:%d wasn't successful", id);
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
                String message = String.format("Cart with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Cart with ID: %d was not removed successfully", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Cart cart) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, cart.getCartId());
            ps.setLong(2, cart.getOrderId());
            ps.setLong(3, cart.getProductId());
            ps.setLong(4, cart.getQuantity());
            ps.setDouble(5, cart.getPrice());
            ps.setDouble(6, cart.getTotal());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Cart Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }


    }

    @Override
    public void update(Cart cart) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1,cart.getQuantity());
            ps.setLong(2,cart.getCartId());
            ps.setLong(3,cart.getProductId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Cart with ID: %d was updated successfully",cart.getCartId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Cart with ID: %d was not updated successfully",cart.getCartId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

}
