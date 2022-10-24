package com.solvd.onlineshop.xml;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IParseXML;

import java.util.List;

import static com.solvd.onlineshop.services.DateAdaptor.StringToDate;

public abstract class AbstractXML {
    protected IParseXML xmlParser;
    protected final String USER_XML_PATH = "src/main/resources/xml/user.xml";
    protected final String ORDER_XML_PATH = "src/main/resources/xml/order.xml";
    protected final String ADDRESS_XML_PATH = "src/main/resources/xml/address.xml";
    protected List<User> expectedUserList = List.of(new User(5, "Laurie", "John", null, "646-000-0001", "laurie@email.com", "laurie1234"), new User(6, "Munif", "Nagi", null, "642-555-0001", "munif@email.com", "Munif1234"));
    protected List<Order> expectedOrderList = List.of(new Order(1, 1109.0, 3, StringToDate("2022-07-23 00:00:00"), 1, 1, 1, 1), new Order(2, 1209.0, 4, StringToDate("2022-09-22 00:00:00"), 2, 1, 1, 2));
    protected List<Address> expectedAddressList = List.of(new Address(1, "USA", "NY", "Queens", "11106", "3735 35th st"), new Address(2, "USA", "VA", "Hrendon", "20170", "1817 Forest Drive"));
}
