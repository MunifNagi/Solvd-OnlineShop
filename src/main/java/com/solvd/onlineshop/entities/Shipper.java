package com.solvd.onlineshop.entities;

public class Shipper {
    private long id;
    private String companyName;
    private boolean isInternational;

    public Shipper(long id, String companyName, boolean isInternational) {
        this.id = id;
        this.companyName = companyName;
        this.isInternational = isInternational;
    }

    public long getShipperId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public boolean isInternational() {
        return isInternational;
    }
}
