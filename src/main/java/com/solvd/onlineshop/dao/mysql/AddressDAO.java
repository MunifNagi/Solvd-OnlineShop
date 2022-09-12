package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IAddressDAO;

import java.sql.*;

public class AddressDAO extends MySQLDAO implements IAddressDAO {
    public  Address getByID(long id){
        Connection con = ConnectionPool.getInstance().getConnection();
        String query ="select * from Address where id=?";
        try(PreparedStatement ps = con.prepareStatement(query)){
            ps.setLong(1,id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) {
                String country = rs.getString("country");
                String state = rs.getString("state");
                String city = rs.getString("city");
                String zipcode = rs.getString("zipcode");
                String street = rs.getString("street");
                Address address = new Address(id, country, state, city, zipcode, street);
                return address;
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
        String query = "DElETE FROM Address WHERE id = ?";
        try(PreparedStatement ps =con.prepareStatement(query)) {
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
    public void create(Address address) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "INSERT INTO Address VALUES(?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setLong(1, address.getAddressId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getState());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getZipcode());
            ps.setString(6, address.getStreet());
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
    public void update(Address address) {
        Connection con = ConnectionPool.getInstance().getConnection();
        String query = "UPDATE Address SET country = ? , state = ? , city = ? , zipcode = ? , street = ? WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1,address.getCountry());
            ps.setString(2,address.getState());
            ps.setString(3,address.getCity());
            ps.setString(4,address.getZipcode());
            ps.setString(5,address.getStreet());
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