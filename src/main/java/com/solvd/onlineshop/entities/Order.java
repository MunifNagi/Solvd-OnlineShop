package com.solvd.onlineshop.entities;

public class Order {
    private long id;
    private double totalPrice;
    private long productsQuantity;
    private String date;
    private long shippingAddressId;
    private int orderStatusId;
    private long paymentId;
    private long shipperId;

    public Order(long id, double totalPrice, long quantity, String date, long shippingAddress, int orderStatusId, long paymentId, long shipperId){
        this.id = id;
        this.totalPrice = totalPrice;
        this.productsQuantity = quantity;
        this.date = date;
        this.shippingAddressId = shippingAddress;
        this.orderStatusId = orderStatusId;
        this.paymentId = paymentId;
        this.shipperId = shipperId;
    }

    public long getOrderId() {
        return id;
    }

    public long getProductsQuantity() {
        return productsQuantity;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getOrderStatusId() {
        return orderStatusId;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public String getDate() {
        return date;
    }
    public long getPaymentId() {
        return paymentId;
    }

    public long getShipperId() {
        return shipperId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public void setShipperId(long shipperId) {
        this.shipperId = shipperId;
    }
}
