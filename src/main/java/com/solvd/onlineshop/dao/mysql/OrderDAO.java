package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.dao.IOrderDAO;
import com.solvd.onlineshop.services.DateAdaptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDAO extends  MySQLDAO implements IOrderDAO {
    private static final Logger logger = LogManager.getLogger(OrderDAO.class);
    private static String readQuery = "SELECT * FROM Order where id=?";
    private static String removeQuery = "DElETE FROM Order WHERE id = ?";
    private static String insertQuery = "INSERT INTO Order VALUES(?,?,?,?,?,?,?,?)";
    private static String updateQuery = "UPDATE User SET order_status = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Order";
    private static String readByStatusIdQuery = "SELECT * FROM Order WHERE order_status_id=?";


    public Order getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                Date date = rs.getDate("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipmentId = rs.getLong("shipment_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipmentId);
                return order;
            }
        } catch (SQLException e) {
            String message = String.format("Getting order with ID:%d wasn't successful", id);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

    public void create(Order order) {
        DateAdaptor dateAdaptor = new DateAdaptor();
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, order.getOrderId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setLong(3, order.getProductsQuantity());
            ps.setDate(4, new java.sql.Date(order.getDate().getTime()));
            ps.setLong(5, order.getShippingAddressId());
            ps.setLong(6, order.getOrderStatusId());
            ps.setLong(7, order.getPaymentId());
            ps.setLong(8, order.getShipmentId());
            ps.executeUpdate();
            logger.info("Inserting record into the Order Table was successful");
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Order Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
    public void update(Order order) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1,order.getOrderStatusId());
            ps.setLong(2,order.getOrderId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Order with ID: %d was updated successfully",order.getOrderId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Order with ID: %d was not updated successfully",order.getOrderId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    public void remove(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(removeQuery)) {
            ps.setLong(1,id);
            if (ps.executeUpdate()>0) {
                String message = String.format("Order with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Order with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }


    @Override
    public List<Order> getAllOrders() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                Date date = rs.getDate("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipmentId = rs.getLong("shipment_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipmentId);
                orderList.add(order);
            }
        } catch (SQLException e) {
            logger.error("Getting all records from Report Table Failed", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return orderList;
    }


    @Override
    public List<Order> getOrderByStatusId(long statusID) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readByStatusIdQuery)) {
            ps.setLong(1,statusID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                Date date = rs.getDate("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipmentId = rs.getLong("shipment_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipmentId);
                orderList.add(order);
            }
        } catch (SQLException e) {
            String message = String.format("Getting Orders by status ID:%d wasn't successful", statusID);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return orderList;
    }
}
