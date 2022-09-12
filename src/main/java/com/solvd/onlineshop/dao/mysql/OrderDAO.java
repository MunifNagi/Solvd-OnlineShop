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
    public Order getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement("select * from Order where id=?")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                long customerId = rs.getLong("customer_id");
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                Order order = new Order(id, totalPrice, productsQuantity, customerId, date, shippingAddressId, orderStatusId);
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
        String query = "INSERT INTO Order VALUES(?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, order.getOrderId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setLong(3, order.getProductsQuantity());
            ps.setLong(4, order.getCustomerId());
            ps.setString(5, order.getDate());
            ps.setLong(6, order.getShippingAddressId());
            ps.setLong(7, order.getOrderStatusID());
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
        String query = "UPDATE User SET order_status = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1,order.getOrderStatusID());
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
        String query = "DElETE FROM Order WHERE id = ?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
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
        String query = "select * from Order";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                long customerId = rs.getLong("customer_id");
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                Order order = new Order(id, totalPrice, productsQuantity, customerId, date, shippingAddressId, orderStatusId);
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
    public List<Order> getOrderByUserId(long userID) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Order> userOrders = new ArrayList<>();
        String query = "select * from Order WHERE customer_id=?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ps.setLong(1,userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                long customerId = rs.getLong("customer_id");
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                Order order = new Order(id, totalPrice, productsQuantity, customerId, date, shippingAddressId, orderStatusId);
                userOrders.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return userOrders;
    }

    @Override
    public List<Order> getOrderByStatusId(long statusID) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Order> orderList = new ArrayList<>();
        String query = "select * from Order WHERE order_status_id=?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ps.setLong(1,statusID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                double totalPrice = rs.getDouble("total_price");
                long productsQuantity = rs.getLong("products_quantity");
                long customerId = rs.getLong("customer_id");
                String date = rs.getString("date");
                long shippingAddressId = rs.getLong("shipping_address_id");
                int orderStatusId = rs.getInt("order_status_id");
                Order order = new Order(id, totalPrice, productsQuantity, customerId, date, shippingAddressId, orderStatusId);
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
