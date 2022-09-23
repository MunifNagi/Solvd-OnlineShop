package com.solvd.onlineshop;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Product;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        IUserService userService = new UserService();
        IAddressService addressService = new AddressService();
        userService.createUser(new User(10,"Alex","John","A","347-000-0000","Alex@test.com","Alex1234"));
        User userObject = userService.getUserByID(10);
        logger.info(userObject);
        userObject.setPhone("000-000-0000");
        userService.updateUser(userObject);

        IParseXML xmlReader= new XMLParser();
        IParseXML jaxb = new JAXBHandler();

        List<User> userList= xmlReader.readXML("src/main/resources/xml/user.xml", User.class);
        logger.info(userList);
        logger.info(jaxb.readXML("src/main/resources/xml/user.xml", User.class));
        List<Address> addressList =  xmlReader.readXML("src/main/resources/xml/address.xml",Address.class);
        logger.info(addressList);
        logger.info(jaxb.readXML("src/main/resources/xml/address.xml", Address.class));

        logger.info("Before Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());
        userList.stream().forEach(user -> userService.createUser(user));
        addressList.stream().forEach(address -> addressService.createAddress(address));
        logger.info("After Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());

        JAXBHandler jaxbHandler = new JAXBHandler();
        jaxbHandler.writeXML(userList, User.class, "src/main/resources/xml/newUsers.xml");
        jaxbHandler.writeXML(userObject,"src/main/resources/xml/newUser.xml");
        jaxbHandler.writeXML(addressService.getAllAddresses(), Address.class,"src/main/resources/xml/newAddress.xml");
    }

}