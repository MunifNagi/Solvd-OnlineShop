package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IDiscountDAO;
import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.entities.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiscountDAO extends MySQLDAO implements IDiscountDAO {
    private static final Logger logger = LogManager.getLogger(DiscountDAO.class);
    private static String readQuery = "SELECT * Discount FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Discount WHERE id = ?";
    private static String insertQuery = "INSERT INTO Discount VALUES(?,?,?)";
    private static String updateQuery = "UPDATE Discount SET name = ?, percentage = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Discount";
    @Override
    public Discount getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                Double percentage = rs.getDouble("percentage");
                Discount discount = new Discount(id, name, percentage);
                return discount;
            }
        } catch (SQLException e) {
            String message = String.format("Getting discount with ID:%d wasn't successful", id);
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
                String message = String.format("Discount with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Discount with ID: %d was not removed successfully", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Discount discount) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, discount.getDiscountId());
            ps.setString(2, discount.getName());
            ps.setDouble(3,discount.getPercentage());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Discount Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Discount discount) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,discount.getName());
            ps.setDouble(2,discount.getPercentage());
            ps.setLong(3,discount.getDiscountId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Discount with ID: %d was updated successfully",discount.getDiscountId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Discount with ID: %d was not updated successfully",discount.getDiscountId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public List<Discount> getAllDiscounts() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Discount> discountList = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readAllQuery)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                Double percentage = rs.getDouble("percentage");
                Discount discount = new Discount(id, name, percentage);
                discountList.add(discount);
            }
        } catch (SQLException e) {
            logger.error("Getting all records from Report Table Failed", e);
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
        return discountList;
    }
}
