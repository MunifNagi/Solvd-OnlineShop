package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.dao.IOrderDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO extends  MySQLDAO implements IOrderDAO {
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
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipperId = rs.getLong("shipper_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipperId);
                return order;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

    public void create(Order order) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, order.getOrderId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setLong(3, order.getProductsQuantity());
            ps.setString(4, order.getDate());
            ps.setLong(5, order.getShippingAddressId());
            ps.setLong(6, order.getOrderStatusId());
            ps.setLong(7, order.getPaymentId());
            ps.setLong(8, order.getShipperId());
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
    public void update(Order order) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1,order.getOrderStatusId());
            ps.setLong(2,order.getOrderId());
            if (ps.executeUpdate() > 0) {
                System.out.println("Update is done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    public void remove(long id) {
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
    public List<Order> getAllOrders() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Order> orderList = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipperId = rs.getLong("shipper_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipperId);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
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
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                long paymentId = rs.getLong("payment_id");
                long shipperId = rs.getLong("shipper_id");
                Order order = new Order(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipperId);
                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return orderList;
    }
}
