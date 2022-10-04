package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.User;

import java.util.List;

public interface IAddressDAO extends IBaseDAO<Address>{
    List<Address> getAllAddresses();
}
