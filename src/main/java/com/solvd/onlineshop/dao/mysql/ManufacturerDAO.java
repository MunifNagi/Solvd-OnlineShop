package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IManufacturerDAO;
import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.entities.Manufacturer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManufacturerDAO extends MySQLDAO implements IManufacturerDAO {
    private static final Logger logger = LogManager.getLogger(ManufacturerDAO.class);
    private static String readQuery = "SELECT * Manufacturer FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Manufacturer WHERE id = ?";
    private static String insertQuery = "INSERT INTO Manufacturer VALUES(?,?,?)";
    private static String updateQuery = "UPDATE Manufacturer SET name = ?, phone = ? WHERE id = ?";

    @Override
    public Manufacturer getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                Manufacturer manufacturer = new Manufacturer(id, name, phone);
                return manufacturer;
            }
        } catch (SQLException e) {
            String message = String.format("Getting manufacturer with ID:%d wasn't successful", id);
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
                String message = String.format("Manufacturer with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Manufacturer with ID: %d was not removed successfully", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Manufacturer manufacturer) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, manufacturer.getManufacturerId());
            ps.setString(2, manufacturer.getManufacturerName());
            ps.setString(3,manufacturer.getManufacturerPhone());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Manufacturer Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Manufacturer manufacturer) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,manufacturer.getManufacturerName());
            ps.setString(2,manufacturer.getManufacturerPhone());
            ps.setLong(3,manufacturer.getManufacturerId());

            if (ps.executeUpdate()>0) {
                String message = String.format("Manufacturer with ID: %d was updated successfully",manufacturer.getManufacturerId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Manufacturer with ID: %d was not updated successfully",manufacturer.getManufacturerId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
