package com.solvd.onlineshop.entities;

public class Shipper {
    private long id;
    private String companyName;

    public Shipper(long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public long getShipperId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }
}
