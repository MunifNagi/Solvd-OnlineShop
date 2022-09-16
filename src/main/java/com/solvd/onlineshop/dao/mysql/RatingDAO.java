package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IRatingDAO;
import com.solvd.onlineshop.entities.Rating;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RatingDAO extends MySQLDAO implements IRatingDAO {
    private static final Logger logger = LogManager.getLogger(RatingDAO.class);
    private static String readQuery = "Select * Rating FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Rating WHERE id = ?";
    private static String insertQuery = "INSERT INTO Rating VALUES(?,?,?,?,?)";
    private static String updateQuery = "UPDATE Rating SET rating = ?, review = ? WHERE id = ?";
    private static String readByProductIdQuery = "Select * Rating FROM WHERE product_id = ?";

    private static String readByReviewerIdQuery = "Select * Rating FROM WHERE user_id = ?";

    @Override
    public Rating getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                long productId = rs.getLong("product_id");
                long userId = rs.getLong("user_id");
                int rating = rs.getInt("rating");
                String review = rs.getString("review");
                Rating rate = new Rating(id, productId, userId, rating, review);
                return rate;
            }
        } catch (SQLException e) {
            String message = String.format("Getting rating with ID:%d wasn't successful", id);
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
                String message = String.format("Rating with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Rating with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void create(Rating rate) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, rate.getRatingId());
            ps.setLong(2, rate.getProductId());
            ps.setLong(3, rate.getReviewerId());
            ps.setInt(4, rate.getRating());
            ps.setString(5, rate.getReview());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Rating Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void update(Rating rating) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setInt(1, rating.getRating());
            ps.setString(2, rating.getReview());
            ps.setLong(3, rating.getRatingId());
            if (ps.executeUpdate()>0) {
                String message = String.format("Rating with ID: %d was updated successfully",rating.getRatingId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Rating with ID: %d was not updated successfully",rating.getRatingId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public List<Rating> getRatingsByProduct(long productId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Rating> ratings = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readByProductIdQuery)) {
            ps.setLong(1,productId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                long userId = rs.getLong("user_id");
                int rating = rs.getInt("rating");
                String review = rs.getString("review");
                Rating rate = new Rating(id, productId, userId, rating, review);
                ratings.add(rate);
            }
        } catch (SQLException e) {
            String message = String.format("Getting Ratings by product ID:%d wasn't successful", productId);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return ratings;
    }

    @Override
    public List<Rating> getRatingsByReviewer(long reviewerId) {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Rating> ratings = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readByProductIdQuery)) {
            ps.setLong(1,reviewerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                long productId = rs.getLong("product_id");
                int rating = rs.getInt("rating");
                String review = rs.getString("review");
                Rating rate = new Rating(id, productId, reviewerId, rating, review);
                ratings.add(rate);
            }
        } catch (SQLException e) {
            String message = String.format("Getting ratings by review ID:%d wasn't successful", reviewerId);
            logger.error(message, e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return ratings;
    }
}
