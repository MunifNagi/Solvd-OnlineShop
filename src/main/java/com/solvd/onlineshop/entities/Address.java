package com.solvd.onlineshop.entities;

public class Address {
    private long id;
    private String country;
    private String state;
    private String city;
    private String zipcode;
    private String street;

    public Address(long addressId, String country, String state, String city, String zipcode, String street) {
        this.id = addressId;
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipcode = zipcode;
        this.street = street;
    }

    public long getAddressId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String getCity() {
        return city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStreet() {
        return street;
    }
}