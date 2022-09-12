package com.solvd.onlineshop.entities;

import com.solvd.onlineshop.dao.mysql.AddressDAO;

import java.sql.SQLException;

public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String phone;
    private String email;
    private String password;

    private long address_id;

    public User(long userId, String firstName, String lastName, String middleName, String phone, String email, String password, int addressID) throws SQLException {
        this.id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address_id = addressID;
    }

    public String getFirstName() {
        return firstName;
    }

    public long getAddress_id() {
        return address_id;
    }

    public long getUserId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress_id(long address_id) {
        this.address_id = address_id;
    }
}
