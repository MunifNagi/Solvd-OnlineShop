package com.solvd.onlineshop.entities;

public class Return {
    private long id;
    private long orderId;
    private String reason;
    private String date;


    public Return(long id, long orderId, String reason, String date) {
        this.id = id;
        this.reason = reason;
        this.date = date;
        this.orderId=orderId;
    }

    public long getReturnId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public long getOrderId() {
        return orderId;
    }
}
