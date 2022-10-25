package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.ICartDAO;
import com.solvd.onlineshop.dao.IReportDAO;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Report;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO extends MySQLDAO implements IReportDAO {
    private static final Logger logger = LogManager.getLogger(ReportDAO.class);
    private static String readQuery = "SELECT * FROM Report where id=?";
    private static String removeQuery = "DElETE FROM Report WHERE id = ?";
    private static String insertQuery = "INSERT INTO Report VALUES(?,?,?,?)";
    private static String updateQuery = "UPDATE Report SET user_id = ?, product_id = ?, order_id = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Report";
    private static String readByUserIdQuery = "SELECT * FROM Report WHERE user_id = ?";
    private static String readByProductIdQuery = "SELECT * FROM Report WHERE product_id = ?";

    @Override
    public Report getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                long userId = rs.getLong("user_id");
                long productId = rs.getLong("product_id");
                long orderId = rs.getLong("order_id");
                Report report = new Report(id, userId, productId, orderId);
                return report;
            }
        } catch (SQLException e) {
            String message = String.format("Getting report with ID:%d wasn't successful", id);
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
        try (PreparedStatement ps = con.prepareStatement(removeQuery)) {
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                String message = String.format("Report with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Report with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Report report) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, report.getReportId());
            ps.setLong(2, report.getUserId());
            ps.setLong(3, report.getProductId());
            ps.setLong(4, report.getOrderId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("Inserting record into the Report Table Failed", e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Report report) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setLong(1, report.getUserId());
            ps.setLong(2, report.getProductId());
            ps.setLong(3, report.getOrderId());
            ps.setLong(4, report.getReportId());
            if (ps.executeUpdate() > 0) {
                String message = String.format("Report with ID: %d was updated successfully", report.getReportId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("report with ID: %d was not updated successfully", report.getReportId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public List<Report> getAllReports() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Report> reports = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readAllQuery)) {
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                long userId = rs.getLong("user_id");
                long productId = rs.getLong("product_id");
                long orderId = rs.getLong("order_id");
                Report report = new Report(id, userId, productId, orderId);
                reports.add(report);
            }
        } catch (SQLException e) {
            logger.error("Getting all records from Report Table Failed");
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
        return reports;
    }

    @Override
    public List<Report> getAllReportsByUserId(long userId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Report> reports = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readAllQuery)) {
            ps.setLong(1, userId);
            rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("id");
                long productId = rs.getLong("product_id");
                long orderId = rs.getLong("order_id");
                Report report = new Report(id, userId, productId, orderId);
                reports.add(report);
            }
        } catch (SQLException e) {
            String message = String.format("Getting Reports by user ID:%d wasn't successful", userId);
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
        return reports;
    }

    @Override
    public List<Report> getAllReportsByProductId(long productId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Report> reports = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement ps = con.prepareStatement(readAllQuery)) {
            ps.setLong(1, productId);
            rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                long userId = rs.getLong("user_id");
                long orderId = rs.getLong("order_id");
                Report report = new Report(id, userId, productId, orderId);
                reports.add(report);
            }
        } catch (SQLException e) {
            String message = String.format("Getting Reports by product ID:%d wasn't successful", productId);
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
        return reports;
    }
}
