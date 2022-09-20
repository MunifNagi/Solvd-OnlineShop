package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

public class JAXBHandler implements IJAXB {

    public void usersToXMl (List<User> users, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File output = new File(filePath);
            marshaller.marshal(new Users(users), output);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> xmlToUsers(String xmlFile){
        File file = new File(xmlFile);
        Users users = new Users();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Users.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            users = (Users) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return users.getUsers();
    }

    @Override
    public void productsToXMl(List<Product> products, String xmlPath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File output = new File(xmlPath);
            marshaller.marshal(new Products(products), output);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> xmlToProducts(String xmlPath) {
        File file = new File(xmlPath);
        Products prodcuts = new Products();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Products.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            prodcuts = (Products) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return prodcuts.getProducts();
    }

    @Override
    public void addressesToXML(List<Address> addressList, String xmlPath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Addresses.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File output = new File(xmlPath);
            marshaller.marshal(new Addresses(addressList), output);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Address> xmlToAddresses(String xmlPath) {
        File file = new File(xmlPath);
        Addresses addressList = new Addresses();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Addresses.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            addressList = (Addresses) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        return addressList.getAddressList();
    }

    public void userToXMl (User user, String filePath) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File output = new File(filePath);
            marshaller.marshal(user, output);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public User xmlToUser(String xmlFile){
        File file = new File(xmlFile);
        User user = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            user = (User) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        System.out.println(user);
        return user;
    }

}
