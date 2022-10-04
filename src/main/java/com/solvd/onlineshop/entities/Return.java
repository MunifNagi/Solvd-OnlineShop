package com.solvd.onlineshop.entities;

import java.util.Date;

public class Return {
    private long id;
    private long orderId;
    private String reason;
    private Date date;


    public Return(long id, long orderId, Date date, String reason) {
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

    public Date getDate() {
        return date;
    }

    public long getOrderId() {
        return orderId;
    }
}
