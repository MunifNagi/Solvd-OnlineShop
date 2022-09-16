package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IReturnDAO;
import com.solvd.onlineshop.entities.Cart;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Return;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnDAO extends MySQLDAO implements IReturnDAO {
    private static String readQuery = "Select * Return FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Return WHERE id = ?";
    private static String insertQuery = "INSERT INTO Return VALUES(?,?,?,?)";
    private static String updateQuery = "UPDATE Cart SET reason = ? WHERE id = ?";
    private static String readAllQuery = "Select * FROM Return";

    @Override
    public Return getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                long orderId = rs.getLong("order_id");
                String date = rs.getString("date");
                String reason = rs.getString("reason");
                Return returnObject = new Return(id,orderId, date, reason);
                return returnObject;
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
    public void create(Return object) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, object.getReturnId());
            ps.setLong(2, object.getOrderId());
            ps.setString(3, object.getDate());
            ps.setString(4, object.getReason());
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
    public void update(Return object) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,object.getReason());
            ps.setLong(2,object.getReturnId());
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
