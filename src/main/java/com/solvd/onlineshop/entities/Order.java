package com.solvd.onlineshop.entities;

public class Order {
    private long id;
    private double totalPrice;
    private long productsQuantity;
    private String date;
    private long shippingAddressId;
    private int orderStatusId;
    private long paymentId;
    private long shipmentId;

    public Order(long id, double totalPrice, long quantity, String date, long shippingAddress, int orderStatusId, long paymentId, long shipmentId){
        this.id = id;
        this.totalPrice = totalPrice;
        this.productsQuantity = quantity;
        this.date = date;
        this.shippingAddressId = shippingAddress;
        this.orderStatusId = orderStatusId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
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

    public long getShipmentId() {
        return shipmentId;
    }

    public void setPaymentId(long paymentId) {
        this.paymentId = paymentId;
    }

    public void setShipmentId(long shipmentId) {
        this.shipmentId = shipmentId;
    }
}
