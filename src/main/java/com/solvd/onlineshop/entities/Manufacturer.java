package com.solvd.onlineshop.entities;

public class Manufacturer {
    private long id;
    private String manufacturerName;

    public Manufacturer(long id, String manufacturerName) {
        this.id = id;
        this.manufacturerName = manufacturerName;
    }

    public long getManufacturerId() {
        return id;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }
}
