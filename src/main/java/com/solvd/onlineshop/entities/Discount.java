package com.solvd.onlineshop.entities;

public class Discount {
    private long id;
    private String name;
    private double percentage;

    public Discount(long id, String name, double percentage) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
    }

    public long getDiscountId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }
}
