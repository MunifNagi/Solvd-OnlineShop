package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IOrderStatusDAO;
import com.solvd.onlineshop.entities.OrderStatus;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusDAO extends MySQLDAO implements IOrderStatusDAO {
    private static String readQuery = "Select * Order_status FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Order_status WHERE id = ?";
    private static String insertQuery = "INSERT INTO Order_status VALUES(?,?)";
    private static String updateQuery = "UPDATE Order_status SET value = ? WHERE id = ?";

    @Override
    public OrderStatus getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String value = rs.getString("value");
                OrderStatus orderStatus = new OrderStatus(id, value);
                return orderStatus;
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
        try(PreparedStatement ps =con.prepareStatement(removeQuery)) {
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
    public void create(OrderStatus orderStatus) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, orderStatus.getStatusId());
            ps.setString(2, orderStatus.getValue());
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
    public void update(OrderStatus orderStatus) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,orderStatus.getValue());
            ps.setLong(2,orderStatus.getStatusId());
            if (ps.executeUpdate() > 0) {
                System.out.println("Update is done");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
