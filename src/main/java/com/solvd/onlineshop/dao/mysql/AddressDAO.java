package com.solvd.onlineshop.dao.mysql;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.ConnectionPool;
import com.solvd.onlineshop.dao.IAddressDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AddressDAO extends MySQLDAO implements IAddressDAO {
    private static final Logger logger = LogManager.getLogger(AddressDAO.class);
    private static String readQuery = "Select * Address FROM WHERE id = ?";
    private static String removeQuery = "DElETE FROM Address WHERE id = ?";
    private static String insertQuery = "INSERT INTO Address VALUES(?,?,?,?,?,?)";
    private static String updateQuery = "UPDATE Address SET country = ? , state = ? , city = ? , zipcode = ? , street = ? WHERE id = ?";
    private static String readAllQuery = "SELECT * FROM Address";
    public Address getByID(long id){
        Connection con = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement ps = con.prepareStatement(readQuery)){
            ps.setLong(1, id);
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
            String message = String.format("Getting address with ID:%d wasn't successful", id);
            logger.error(message, e);
            return null;
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
                String message = String.format("Address with ID: %d was removed successfully", id);
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Address with ID: %d was not removed from DateBase", id);
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }

    }

    @Override
    public void create(Address address) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(insertQuery)) {
            ps.setLong(1, address.getAddressId());
            ps.setString(2, address.getCountry());
            ps.setString(3, address.getState());
            ps.setString(4, address.getCity());
            ps.setString(5, address.getZipcode());
            ps.setString(6, address.getStreet());
            ps.executeUpdate();
        }
        catch (SQLException e) {
            logger.error("Inserting record into the Address Table Failed",e);
        }
        finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public void update(Address address) {
        Connection con = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement ps = con.prepareStatement(updateQuery)) {
            ps.setString(1,address.getCountry());
            ps.setString(2,address.getState());
            ps.setString(3,address.getCity());
            ps.setString(4,address.getZipcode());
            ps.setString(5,address.getStreet());
            if (ps.executeUpdate()>0) {
                String message = String.format("Address with ID: %d was updated successfully",address.getAddressId());
                logger.info(message);
            }
        } catch (SQLException e) {
            String message = String.format("Address with ID: %d was not updated successfully",address.getAddressId());
            logger.error(message);
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        Connection con = ConnectionPool.getInstance().getConnection();
        List<Address> addressList = new ArrayList<>();
        try(PreparedStatement ps =con.prepareStatement(readAllQuery)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String country = rs.getString("country");
                String state = rs.getString("state");
                String city = rs.getString("city");
                String zipcode = rs.getString("zipcode");
                String street = rs.getString("street");
                Address address = new Address(id, country, state, city, zipcode, street);
                addressList.add(address);
            }
        } catch (SQLException e) {
            logger.error("Getting all records from Address Table Failed");
        } finally {
            ConnectionPool.getInstance().returnConnection(con);
        }
        return addressList;
    }
}
