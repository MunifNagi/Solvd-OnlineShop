package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IPaymentDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO extends MySQLDAO implements IPaymentDAO {
    private static final Logger logger = LogManager.getLogger(PaymentDAO.class);
    private static String readQuery = "Select * Payment FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Payment WHERE id = ?";
    private static String insertQuery = "INSERT INTO Payment VALUES(?,?,?,?,?)";
    private static String updateQuery = "UPDATE Payment SET type = ?, amount= ? WHERE id = ?";
    private static String readByUserIdQuery = "Select * Payment FROM WHERE user_id = ?";
    @Override
    public Payment getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String type = rs.getString("type");
                Double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                long userId = rs.getLong("user_id");
                Payment payment = new Payment(id, type, amount, date, userId);
                return payment;
            }
        } catch (SQLException e) {
            String message = String.format("Getting payment with ID:%d wasn't successful", id);
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
                String message = String.format("Payment with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Payment with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Payment payment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, payment.getPaymentId());
            ps.setString(2, payment.getType());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getDate());
            ps.setLong(5, payment.getUserId());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Payment Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Payment payment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, payment.getType());
            ps.setDouble(2, payment.getAmount());
            ps.setLong(3, payment.getPaymentId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Payment with ID: %d was updated successfully",payment.getPaymentId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Payment with ID: %d was not updated successfully",payment.getPaymentId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public Payment getPayemntByUserId(long userId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readByUserIdQuery)){
            ps.setLong(1,userId);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                long id = rs.getLong("id");
                String type = rs.getString("type");
                Double amount = rs.getDouble("amount");
                String date = rs.getString("date");
                Payment payment = new Payment(id, type, amount, date, userId);
                return payment;
            }
        } catch (SQLException e) {
            String message = String.format("Getting payments by user ID:%d wasn't successful", userId);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }
}
