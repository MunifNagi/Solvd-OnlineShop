package com.solvd.onlineshop.xml;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.XMLParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class XMLParserTest extends AbstractXML {
    @BeforeClass
    public void setUp() {
        xmlParser = new XMLParser();
    }

    @Test
    public void testReadXML() {
        List<User> userList = xmlParser.readXML(USER_XML_PATH, User.class);
        Assert.assertEquals(userList, expectedUserList, "Users from XML must match expected users");
        List<Address> addressList = xmlParser.readXML(ADDRESS_XML_PATH, Address.class);
        Assert.assertEquals(addressList, expectedAddressList, "Addresses from XML must match expected addresses");
        List<Order> orderList = xmlParser.readXML(ORDER_XML_PATH, Order.class);
        Assert.assertEquals(orderList, expectedOrderList, "Orders from XML must match expected users");
    }
}
