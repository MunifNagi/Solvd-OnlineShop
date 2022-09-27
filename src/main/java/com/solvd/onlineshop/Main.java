package com.solvd.onlineshop;


import com.solvd.onlineshop.entities.*;
import com.solvd.onlineshop.services.*;
import com.solvd.onlineshop.services.jdbcImp.AddressService;
import com.solvd.onlineshop.services.jdbcImp.OrderService;
import com.solvd.onlineshop.services.jdbcImp.PaymentService;
import com.solvd.onlineshop.services.jdbcImp.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        IUserService userService = new UserService();
        IAddressService addressService = new AddressService();
        IOrderService orderService = new OrderService();
        IPaymentService paymentService = new PaymentService();
        userService.createUser(new User(3000,"Alex","John","A","347-000-0000","Alex@test.com","Alex1234"));
        paymentService.createPayment(new Payment(3,"VISA",1400,new Date(),3000));
        System.out.println(paymentService.getAllPayments());
        User userObject = userService.getUserByID(3000);
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
        List<Order> orderList =  xmlReader.readXML("src/main/resources/xml/order.xml",Order.class);
        logger.info(jaxb.readXML("src/main/resources/xml/order.xml", Order.class));

        logger.info("Before Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());
        Display.print(orderService.getAllOrders());
        userList.stream().forEach(user -> userService.createUser(user));
        addressList.stream().forEach(address -> addressService.createAddress(address));
        orderList.stream().forEach(order1 -> orderService.createOrder(order1));
        logger.info("After Services Insertions");
        Display.print(userService.getAllUsers());
        Display.print(addressService.getAllAddresses());
        Display.print(orderService.getAllOrders());

        JsonMapper jsonMapper = new JsonMapper();
        List<User> usersList = jsonMapper.readJSON("src/main/resources/json/user.json", User.class);
        List<Order> ordersList = jsonMapper.readJSON("src/main/resources/json/order.json", Order.class);
        List<Payment> paymentList = jsonMapper.readJSON("src/main/resources/json/payment.json", Payment.class);
        jsonMapper.writeJSON(usersList, "src/main/resources/json/new-user.json");
        jsonMapper.writeJSON(ordersList, "src/main/resources/json/new-order.json");
        jsonMapper.writeJSON(paymentList, "src/main/resources/json/new-payment.json");
        IUserService userS = new com.solvd.onlineshop.services.myBatisImp.UserService();
        userS.createUser(new User(2,"munifff","m","A","347-000-0000","Alex@test.com","Alex1234"));
        System.out.println(userS.getUserByID(2000));
    }

}