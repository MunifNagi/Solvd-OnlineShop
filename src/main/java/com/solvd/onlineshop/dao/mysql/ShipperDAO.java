package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IShipperDAO;
import com.solvd.onlineshop.entities.Manufacturer;
import com.solvd.onlineshop.entities.Shipper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShipperDAO extends MySQLDAO implements IShipperDAO {
    private static final Logger logger = LogManager.getLogger(ShipperDAO.class);
    private static String readQuery = "SELECT * FROM Shipper WHERE id = ?";
    private static String removeQuery = "DElETE FROM Shipper WHERE id = ?";
    private static String insertQuery = "INSERT INTO Shipper VALUES(?,?,?)";
    private static String updateQuery = "UPDATE Shipper SET company_name = ? WHERE id = ?";

    @Override
    public Shipper getByID(long id) {
        ResultSet rs = null;
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps =con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if(rs.next()) {
                String name = rs.getString("company_name");
                boolean isInternational = rs.getBoolean("internationalShipping");
                Shipper shipper = new Shipper(id, name, isInternational);
                return shipper;
            }
        } catch (SQLException e) {
            String message = String.format("Getting shipper with ID:%d wasn't successful", id);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
            if(rs != null) {
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
            ps.setLong(1, id);
            if (ps.executeUpdate()>0) {
                String message = String.format("Shipper with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Shipper with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Shipper shipper) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, shipper.getShipperId());
            ps.setString(2, shipper.getCompanyName());
            ps.setBoolean(3, shipper.isInternational());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Shipper Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Shipper shipper) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,shipper.getCompanyName());
            ps.setLong(2,shipper.getShipperId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Shipper with ID: %d was updated successfully",shipper.getShipperId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Shipper with ID: %d was not updated successfully",shipper.getShipperId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
