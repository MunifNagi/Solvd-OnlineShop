package com.solvd.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

public class Payment {
    @JsonProperty
    private long id;
    @JsonProperty
    private String type;
    @JsonProperty
    private double amount;
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
    private Date date;
    @JsonProperty
    private long userId;

    public Payment(long id, String type, double amount, Date date, long userId) {
        this.id = id;
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.userId = userId;
    }

    public Payment() {
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", amount=" + amount +
                ", date=" + date +
                '}';
    }

    @JsonProperty("id")
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

    public void setType(String type) {
        this.type = type;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return id == payment.id && Double.compare(payment.amount, amount) == 0 && userId == payment.userId && type.equals(payment.type) && date.equals(payment.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, amount, date, userId);
    }
}
