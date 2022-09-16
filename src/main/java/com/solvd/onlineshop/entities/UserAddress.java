package com.solvd.onlineshop.entities;

public class UserAddress {
    private long id;
    private long userId;
    private long addressId;

    public UserAddress(long id, long userId, long addressId) {
        this.id=id;
        this.userId = userId;
        this.addressId = addressId;
    }

    public long getUserId() {
        return userId;
    }

    public long getAddressId() {
        return addressId;
    }

    public long getUserAddressId() {
        return id;
    }
}
