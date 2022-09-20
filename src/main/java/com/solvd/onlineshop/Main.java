package com.solvd.onlineshop;

import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.*;

public class Main {
    public static void main(String[] args) {
        UserService us = new UserService();
        us.createUser(new User(1,"Alex","John","A","347-000-0000","Alex@test.com","Alex1234"));
        User user = us.getUserByID(1);
        System.out.println(user);
        user.setPhone("000-000-0000");
        us.updateUser(user);
        IParseXML xmlreadr= new XMLParser();
        xmlreadr.readUserXML("src/main/resources/xml/user.xml");
        xmlreadr.readAddressXML("src/main/resources/xml/address.xml");
        xmlreadr.readProductXML("src/main/resources/xml/product.xml");
    }

}