package com.solvd.onlineshop;


import com.solvd.onlineshop.entities.*;
import com.solvd.onlineshop.services.*;
import com.solvd.onlineshop.services.jdbcImp.OrderService;
import com.solvd.onlineshop.services.jdbcImp.PaymentService;
import com.solvd.onlineshop.services.myBatisImp.AddressService;
import com.solvd.onlineshop.services.myBatisImp.ProductService;
import com.solvd.onlineshop.services.myBatisImp.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;
import java.util.List;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IUserService;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    public static void main(String[] args) {
        IUserService userService = new com.solvd.onlineshop.services.jdbcImp.UserService();
        IAddressService addressService = new com.solvd.onlineshop.services.jdbcImp.AddressService();
        IOrderService orderService = new com.solvd.onlineshop.services.jdbcImp.OrderService();
        IPaymentService paymentService = new PaymentService();
        userService.createUser(new User(22,"Alex","John","A","347-000-0000","Alex@test.com","Alex1234"));
        paymentService.createPayment(new Payment(3,"VISA",1400,new Date(),22));
        System.out.println(paymentService.getAllPayments());
        User userObject = userService.getUserByID(22);
        logger.info(userObject);
        userObject.setPhone("000-000-0000");
        userService.updateUser(userObject);

        IParseXML xmlReader= new XMLParser();
        IParseXML jaxb = new JAXBHandler();
        JAXBHandler jaxbw = new JAXBHandler();;

        List<User> userList= xmlReader.readXML("src/main/resources/xml/user.xml", User.class);
        logger.info(userList);
        logger.info(jaxb.readXML("src/main/resources/xml/user.xml", User.class));
        List<Address> addressList =  xmlReader.readXML("src/main/resources/xml/address.xml",Address.class);
        logger.info(addressList);
        logger.info(jaxb.readXML("src/main/resources/xml/address.xml", Address.class));
        List<Order> orderList =  xmlReader.readXML("src/main/resources/xml/order.xml",Order.class);
        logger.info(jaxb.readXML("src/main/resources/xml/order.xml", Order.class));
        jaxbw.writeXML(userList, User.class, "src/main/resources/xml/new-user.xml");
        jaxbw.writeXML(addressList, Address.class, "src/main/resources/xml/new-address.xml");
        jaxbw.writeXML(orderList, Order.class, "src/main/resources/xml/new-order.xml");

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

        IProductService productService2 = new ProductService();
        IUserService userService2= new UserService();
        IAddressService addressService2 = new AddressService();
        IPaymentService paymentService2 = new com.solvd.onlineshop.services.myBatisImp.PaymentService();

        User newUser = new User(10, "James", "m",null, "347-000-0000", "James@test.com", "James1234");
        userService2.createUser(newUser);
        newUser.setPhone("347-001-0001");
        userService2.updateUser(newUser);
        System.out.println(userService2.getAllUsers());
        userService2.removeUser(10);
        System.out.println(userService2.getAllUsers());
        addressService2.getAllAddresses();
        paymentService2.createPayment(new Payment(5,"VISA",1400,new Date(),22));
        Payment pay = paymentService2.getPaymentByID(5);
        pay.setType("MasterCard");
        paymentService2.updatePayment(pay);
        paymentService2.removePayment(5);

        List<Product> productList = xmlReader.readXML("src/main/resources/xml/product.xml",Product.class);
        productList.stream().forEach(product -> productService2.createProduct(product));
    }

}