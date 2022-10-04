package com.solvd.onlineshop.entities;

import java.util.Date;

public class Shipment {
    private long id;
    private String trackingNumber;
    private Date date;
    private long shipperId;

    public Shipment(long id, String trackingNumber, Date date, long shipperId) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.date = date;
        this.shipperId = shipperId;
    }

    public long getShipmentId() {
        return id;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Date getDate() {
        return date;
    }

    public long getShipperId() {
        return shipperId;
    }
}
