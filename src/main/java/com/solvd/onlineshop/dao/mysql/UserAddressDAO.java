package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IUserAddressDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.UserAddress;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressDAO extends MySQLDAO implements IUserAddressDAO {
    private static String readQuery = "Select * UserAddress FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM UserAddress WHERE id = ?";
    private static String insertQuery = "INSERT INTO UserAddress VALUES(?,?,?)";
    private static String updateQuery = "UPDATE UserAddress SET address_id = ? WHERE id = ?";
    @Override
    public UserAddress getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                long userId = rs.getLong("user_id");
                long addressId = rs.getLong("address_id");
                UserAddress userAddress = new UserAddress(id, userId, addressId);
                return userAddress;
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
    public void create(UserAddress object) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, object.getUserAddressId());
            ps.setLong(2, object.getUserId());
            ps.setLong(3, object.getAddressId());
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
    public void update(UserAddress object) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1,object.getAddressId());
            ps.setLong(2,object.getUserAddressId());
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
