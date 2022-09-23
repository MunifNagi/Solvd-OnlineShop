package com.solvd.onlineshop.entities;

import java.util.Date;

public class Payment {

    private long id;
    private String type;
    private double amount;
    private Date date;
    private long userId;

    public Payment(long id, String type, double amount, Date date, long userId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
    }

    public long getPaymentId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public Date getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }
}
