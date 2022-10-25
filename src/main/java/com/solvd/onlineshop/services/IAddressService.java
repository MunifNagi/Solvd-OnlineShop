package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Product;

import java.util.List;

public interface IAddressService {
    Address getAddressByID(long id);

    List<Address> getAllAddresses();

    void removeAddress(long id);

    void createAddress(Address address);

    void updateAddress(Address address);
}
