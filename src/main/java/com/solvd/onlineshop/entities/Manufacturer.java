package com.solvd.onlineshop.entities;

import java.util.Objects;

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

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manufacturer that = (Manufacturer) o;
        return id == that.id && manufacturerName.equals(that.manufacturerName) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, manufacturerName, phone);
    }
}
