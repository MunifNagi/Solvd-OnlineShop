package com.solvd.onlineshop.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
import java.util.Objects;

@XmlRootElement(name="Order")
@XmlAccessorType(XmlAccessType.FIELD)
public class Order {
    @XmlElement
    @JsonProperty
    private long id;
    @JsonProperty
    @XmlElement(name = "total_price")
    private double totalPrice;
    @JsonProperty
    @XmlElement(name = "products_quantity")
    private long productsQuantity;
    @XmlElement
    @JsonProperty
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy/MM/dd")
    private Date date;
    @JsonProperty
    @XmlElement(name = "shipping_address_id")
    private long shippingAddressId;
    @JsonProperty
    @XmlElement(name = "status_id")
    private int orderStatusId;
    @JsonProperty
    @XmlElement(name = "payment_id")
    private long paymentId;
    @JsonProperty
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
    @JsonProperty("id")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Double.compare(order.totalPrice, totalPrice) == 0 && productsQuantity == order.productsQuantity && shippingAddressId == order.shippingAddressId && orderStatusId == order.orderStatusId && paymentId == order.paymentId && shipmentId == order.shipmentId && date.equals(order.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, productsQuantity, date, shippingAddressId, orderStatusId, paymentId, shipmentId);
    }
}
