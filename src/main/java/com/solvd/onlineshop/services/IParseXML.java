package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.entities.User;

import java.util.List;

public interface IParseXML {
    List<User> readUserXML(String xmlPath);
    List<Address> readAddressXML(String xmlPath);
    List<Product> readProductXML(String xmlPath);
}
