package com.solvd.onlineshop.entities;

public class OrderStatus {
    private long id;
    private String value;

    public OrderStatus(long id, String value) {
        this.id = id;
        this.value = value;
    }

    public long getStatusId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}
