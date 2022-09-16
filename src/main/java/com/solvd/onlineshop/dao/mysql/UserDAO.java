package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.dao.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends MySQLDAO implements IUserDAO {

    private static String readQuery = "SELECT * FROM User where id=?";
    private static String removeQuery = "DElETE FROM User WHERE id = ?";
    private static String insertQuery = "INSERT INTO User VALUES(?,?,?,?,?,?,?)";
    private static String updateQuery = "UPDATE User SET  phone = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM User";


    public void create(User user) {
         Connection con = ConnectionPool.getInstance().getConnection();
         try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
             ps.setLong(1, user.getUserId());
             ps.setString(2, user.getFirstName());
             ps.setString(3, user.getLastName());
             ps.setString(4, user.getMiddleName());
             ps.setString(5, user.getPhone());
             ps.setString(6, user.getEmail());
             ps.setString(7, user.getPassword());
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

    public void update(User user) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,user.getPhone());
            ps.setLong(2,user.getUserId());
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
    public User getByID(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(id, firstName, lastName, middleName, phone, email, password);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return null;
    }

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
    public List<User> getAllUsers() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                User user = new User(id, firstName, lastName, middleName, phone, email, password);
                userList.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return userList;
    }
}
