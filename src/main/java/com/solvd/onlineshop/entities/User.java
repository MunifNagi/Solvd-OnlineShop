package com.solvd.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.solvd.onlineshop.dao.mysql.AddressDAO;

import javax.xml.bind.annotation.*;
import java.sql.SQLException;

@XmlRootElement(name="User")
@XmlType(propOrder = {"id","firstName","lastName","middleName","phone","email","password"})
@JsonPropertyOrder({ "id","firstName","lastName","middleName","phone","email","password" })
@XmlAccessorType(XmlAccessType.FIELD)
public class User {

    @XmlElement
    @JsonProperty("id")
    private long id;

    @XmlElement(name = "first_name")
    @JsonProperty("fname")
    private String firstName;
    @XmlElement(name = "last_name")
    @JsonProperty("lname")
    private String lastName;
    @XmlElement(name = "middle_name")
    @JsonProperty("mname")
    private String middleName;
    @XmlElement
    @JsonProperty
    private String phone;
    @XmlElement
    @JsonProperty
    private String email;
    @XmlElement
    @JsonProperty
    private String password;


    public User(long id, String firstName, String lastName, String middleName, String phone, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public User() {
    }

    public String getFirstName() {
        return firstName;
    }
    @JsonProperty("id")
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
                "id='" + id + '\'' +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserId(long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
