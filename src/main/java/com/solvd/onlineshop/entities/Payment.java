package com.solvd.onlineshop.entities;

public class Payment {

    private long id;
    private String type;
    private double amount;
    private String date;
    private long userId;

    public Payment(long id, String type, double amount, String date, long userId) {
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

    public String getDate() {
        return date;
    }

    public long getUserId() {
        return userId;
    }


}
