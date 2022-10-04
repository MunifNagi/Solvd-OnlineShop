package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IOrderStatusDAO;
import com.solvd.onlineshop.entities.OrderStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderStatusDAO extends MySQLDAO implements IOrderStatusDAO {
    private static final Logger logger = LogManager.getLogger(OrderStatusDAO.class);
    private static String readQuery = "Select * FROM Order_status WHERE id = ?";
    private static String removeQuery = "DElETE FROM Order_status WHERE id = ?";
    private static String insertQuery = "INSERT INTO Order_status VALUES(?,?)";
    private static String updateQuery = "UPDATE Order_status SET value = ? WHERE id = ?";

    @Override
    public OrderStatus getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String value = rs.getString("value");
                OrderStatus orderStatus = new OrderStatus(id, value);
                return orderStatus;
            }
        } catch (SQLException e) {
            String message = String.format("Getting order status with ID:%d wasn't successful", id);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return null;
    }

    @Override
    public void remove(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(removeQuery)) {
            ps.setLong(1,id);
            if (ps.executeUpdate()>0) {
                String message = String.format("OrderStatus with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("OrderStatus with ID: %d was not removed from DateBase", id);
            logger.error(message);
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
        }
        catch (SQLException e) {
            logger.error("Inserting record into the OrderStatus Table Failed",e);
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
            if (ps.executeUpdate()>0) {
                String message = String.format("OrderStatus with ID: %d was updated successfully",orderStatus.getStatusId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("OrderStatus with ID: %d was not updated successfully",orderStatus.getStatusId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
