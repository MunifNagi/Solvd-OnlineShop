package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.dao.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends MySQLDAO implements IUserDAO {

     public void create(User user) {
         Connection con = ConnectionPool.getInstance().getConnection();
         String query = "INSERT INTO User VALUES(?,?,?,?,?,?,?,?)";
         try (PreparedStatement ps = con.prepareStatement(query)) {
             ps.setLong(1, user.getUserId());
             ps.setString(2, user.getFirstName());
             ps.setString(3, user.getLastName());
             ps.setString(4, user.getMiddleName());
             ps.setString(5, user.getPhone());
             ps.setString(6, user.getEmail());
             ps.setString(7, user.getPassword());
             ps.setLong(8, user.getAddress_id());
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
        String query = "UPDATE User SET address_id = ? , phone = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1,user.getAddress_id());
            ps.setString(2,user.getPhone());
            ps.setLong(3,user.getUserId());
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
        try(PreparedStatement ps = con.prepareStatement("select * from User where id=?")) {
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int addressID = rs.getInt("address_id");
                User user = new User(id, firstName, lastName, middleName, phone, email, password, addressID);
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

//        Statement stmt = con.createStatement();
//        String query = String.format("select * from User where id=%d",id);
//        ResultSet rs = stmt.executeQuery(query);
        return null;
    }

    public void remove(long id) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "DElETE FROM User WHERE id = ?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
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
        String query = "select * from User";
        try(PreparedStatement ps =con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String middleName = rs.getString("middle_name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                String password = rs.getString("password");
                int addressID = rs.getInt("address_id");
                User user = new User(id, firstName, lastName, middleName, phone, email, password, addressID);
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
