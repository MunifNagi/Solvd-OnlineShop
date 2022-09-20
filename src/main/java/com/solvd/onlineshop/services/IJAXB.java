package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.*;

import java.util.List;

public interface IJAXB {
    User xmlToUser(String xmlPath);
    void userToXMl(User user, String xmlPath);
    void usersToXMl (List<User> users, String filePath);
    List<User> xmlToUsers(String xmlPath);
    void productsToXMl(List<Product> products, String xmlPath);
    List<Product> xmlToProducts(String xmlPath);
    void addressesToXML(List<Address> addressList, String xmlPath);
    List<Address> xmlToAddresses(String xmlPath);

}
