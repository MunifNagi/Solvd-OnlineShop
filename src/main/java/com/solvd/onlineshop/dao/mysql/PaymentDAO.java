package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IPaymentDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Payment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO extends MySQLDAO implements IPaymentDAO {
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
    public void create(Payment payment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, payment.getPaymentId());
            ps.setString(2, payment.getType());
            ps.setDouble(3, payment.getAmount());
            ps.setString(4, payment.getDate());
            ps.setLong(5, payment.getUserId());
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
    public void update(Payment payment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1, payment.getType());
            ps.setDouble(2, payment.getAmount());
            ps.setLong(3, payment.getPaymentId());
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
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }
}
