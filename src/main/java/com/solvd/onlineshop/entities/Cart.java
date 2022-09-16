package com.solvd.onlineshop.entities;

public class Cart {
    private long id;
    private long orderId;
    private long productId;
    private long quantity;
    private double price;
    private double total;


    public Cart(long id, long orderId, long productId, long quantity, double price, double total) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
        this.total= total;
    }

    public long getPurchasedProductId() {
        return id;
    }

    public long getOrderId() {
        return orderId;
    }

    public long getProductId() {
        return productId;
    }

    public long getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotal() {
        return total;
    }
}
