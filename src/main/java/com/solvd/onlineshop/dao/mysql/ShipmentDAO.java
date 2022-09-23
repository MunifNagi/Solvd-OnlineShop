package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IShipmentDAO;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Shipment;
import com.solvd.onlineshop.exception.InvalidDataBaseConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ShipmentDAO extends MySQLDAO implements IShipmentDAO {
    private static final Logger logger = LogManager.getLogger(ShipmentDAO.class);
    private static String readQuery = "SELECT * FROM Shipment where id = ?";
    private static String removeQuery = "DElETE FROM Shipment WHERE id = ?";
    private static String insertQuery = "INSERT INTO Shipment VALUES(?,?,?,?)";
    private static String updateQuery = "UPDATE Shipment SET shipper_id = ? WHERE id = ?";
    @Override
    public Shipment getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String trackingNumber = rs.getString("tracking_number");
                Date date = rs.getDate("date");
                long shipperId = rs.getLong("shipper_id");
                Shipment shipment = new Shipment(id, trackingNumber, date, shipperId);
                return shipment;
            }
        } catch (SQLException e) {
            String message = String.format("Getting shipment with ID:%d wasn't successful", id);
            logger.error(message, e);
            throw new InvalidDataBaseConnection(e);
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
                String message = String.format("Shipment with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Shipment with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Shipment shipment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, shipment.getShipmentId());
            ps.setString(2, shipment.getTrackingNumber());
            ps.setDate(3, new java.sql.Date(shipment.getDate().getTime()));
            ps.setLong(4, shipment.getShipper_id());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Shipment Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Shipment shipment) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1,shipment.getShipper_id());
            ps.setLong(2,shipment.getShipmentId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Shipment with ID: %d was updated successfully",shipment.getShipmentId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Shipment with ID: %d was not updated successfully",shipment.getShipmentId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }
}
