package com.solvd.onlineshop.entities;

public class Manufacturer {
    private long id;
    private String manufacturerName;
    private String phone;

    public Manufacturer(long id, String manufacturerName, String phone) {
        this.id = id;
        this.manufacturerName = manufacturerName;
        this.phone = phone;
    }

    public long getManufacturerId() {
        return id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public String getManufacturerPhone() {
        return phone;
    }
}
