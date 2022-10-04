package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IUserAddressDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.UserAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserAddressDAO extends MySQLDAO implements IUserAddressDAO {
    private static final Logger logger = LogManager.getLogger(UserAddressDAO.class);
    private static String readQuery = "Select * FROM UserAddress WHERE id = ?";
    private static String removeQuery = "DElETE FROM UserAddress WHERE id = ?";
    private static String insertQuery = "INSERT INTO UserAddress VALUES(?,?,?)";
    private static String updateQuery = "UPDATE UserAddress SET address_id = ? WHERE id = ?";
    @Override
    public UserAddress getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                long userId = rs.getLong("user_id");
                long addressId = rs.getLong("address_id");
                UserAddress userAddress = new UserAddress(id, userId, addressId);
                return userAddress;
            }
        } catch (SQLException e) {
            String message = String.format("Getting user address with ID:%d wasn't successful", id);
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
                String message = String.format("UserAddress with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("UserAddress with ID: %d was not removed from DateBase", id);
            logger.error(message);
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
        }
        catch (SQLException e) {
            logger.error("Inserting record into the UserAddress Table Failed",e);
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
            if (ps.executeUpdate()>0) {
                String message = String.format("UserAddress with ID: %d was updated successfully",object.getUserAddressId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("UserAddress with ID: %d was not updated successfully",object.getUserAddressId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
