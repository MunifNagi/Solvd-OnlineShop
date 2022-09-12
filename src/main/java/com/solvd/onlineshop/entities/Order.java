package com.solvd.onlineshop.entities;

public class Order {
    private long id;
    private double totalPrice;
    private long productsQuantity;
    private long customerId;
    private String date;
    private long shippingAddressId;
    private int orderStatusID;



    public Order(long id, double totalPrice, long quantity, long customerId, String date, long shippingAddress, int orderStatusID){
        this.id = id;
        this.totalPrice = totalPrice;
        this.productsQuantity = quantity;
        this.customerId = customerId;
        this.date = date;
        this.shippingAddressId = shippingAddress;
        this.orderStatusID = orderStatusID;
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

    public int getOrderStatusID() {
        return orderStatusID;
    }

    public long getCustomerId() {
        return customerId;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public String getDate() {
        return date;
    }

}
