package com.solvd.onlineshop.entities;

public class Shipment {
    private long id;
    private String trackingNumber;
    private String date;
    private long shipper_id;

    public Shipment(long id, String trackingNumber, String date, long shipper_id) {
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

    public String getDate() {
        return date;
    }

    public long getShipper_id() {
        return shipper_id;
    }
}
