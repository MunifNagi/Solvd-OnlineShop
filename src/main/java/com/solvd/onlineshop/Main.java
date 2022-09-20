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
        System.out.println(userObject);
        userObject.setPhone("000-000-0000");
        userService.updateUser(userObject);

        IParseXML xmlreadr= new XMLParser();
        List<User> userList= xmlreadr.readUserXML("src/main/resources/xml/user.xml");
        List<Address> addressList =  xmlreadr.readAddressXML("src/main/resources/xml/address.xml");
        List<Product> productList = xmlreadr.readProductXML("src/main/resources/xml/product.xml");
        logger.info("Before Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());
        userList.stream().forEach(user -> userService.createUser(user));
        addressList.stream().forEach(address -> addressService.createAddress(address));
        logger.info("After Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());

        IJAXB jaxb = new JAXBHandler();
        List<Address> addressList2 = jaxb.xmlToAddresses("src/main/resources/xml/address.xml");
        jaxb.usersToXMl(userService.getAllUsers(),"src/main/resources/xml/newUsers.xml");
        jaxb.productsToXMl(productList, "src/main/resources/xml/newProducts.xml");
        jaxb.addressesToXML(addressList2, "src/main/resources/xml/newAddresses.xml");
    }

}