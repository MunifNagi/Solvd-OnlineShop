package com.solvd.onlineshop.entities;

import java.util.Date;

public class Shipment {
    private long id;
    private String trackingNumber;
    private Date date;
    private long shipper_id;

    public Shipment(long id, String trackingNumber, Date date, long shipper_id) {
        this.id = id;
        this.trackingNumber = trackingNumber;
        this.date = date;
        this.shipper_id = shipper_id;
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

    public long getShipper_id() {
        return shipper_id;
    }
}
