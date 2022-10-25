package com.solvd.onlineshop.entities;

import java.util.Objects;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id && Double.compare(discount.percentage, percentage) == 0 && name.equals(discount.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, percentage);
    }
}
