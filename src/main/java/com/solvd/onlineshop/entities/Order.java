package com.solvd.onlineshop.entities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement(name="Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement
    private long id;
    @XmlElement(name = "total_price")
    private double totalPrice;
    @XmlElement(name = "products_quantity")
    private long productsQuantity;
    @XmlElement
    private Date date;
    @XmlElement(name = "shipping_address_id")
    private long shippingAddressId;
    @XmlElement(name = "status_id")
    private int orderStatusId;
    @XmlElement(name = "payment_id")
    private long paymentId;
    @XmlElement(name = "shipment_id")
    private long shipmentId;

    public Order() {
    }

    public Order(long id, double totalPrice, long quantity, Date date, long shippingAddress, int orderStatusId, long paymentId, long shipmentId){
        this.id = id;
        this.totalPrice = totalPrice;
        this.productsQuantity = quantity;
        this.date = date;
        this.shippingAddressId = shippingAddress;
        this.orderStatusId = orderStatusId;
        this.paymentId = paymentId;
        this.shipmentId = shipmentId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", productsQuantity=" + productsQuantity +
                ", date=" + date +
                '}';
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

    public Date getDate() {
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

    public void setOrderId(long id) {
        this.id = id;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProductsQuantity(long productsQuantity) {
        this.productsQuantity = productsQuantity;
    }

    public void setOrderDate(Date date) {
        this.date = date;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public void setOrderStatusId(int orderStatusId) {
        this.orderStatusId = orderStatusId;
    }
}
