package com.solvd.onlineshop.entities;

public class Report {
    private long id;
    private long userId;
    private long productId;
    private long orderId;

    public Report(long id, long userId, long productId, long orderId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.orderId = orderId;
    }

    public long getReportId() {
        return id;
    }

    public long getUserId() {
        return userId;
    }

    public long getProductId() {
        return productId;
    }

    public long getOrderId() {
        return orderId;
    }
}
