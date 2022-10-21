package com.solvd.onlineshop.xml;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IParseXML;
import com.solvd.onlineshop.services.XMLParser;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.onlineshop.services.DateAdaptor.StringToDate;

public class XMLParserTest extends AbstractXML {
    @BeforeClass
    public void setUp() {
        xmlParser = new XMLParser();
        userXMLPath = "src/main/resources/xml/user.xml";
        orderXMLPath = "src/main/resources/xml/order.xml";
        addressXMLPath = "src/main/resources/xml/address.xml";
        expectedUserList.add(new User(5, "Laurie", "John", null, "646-000-0001", "laurie@email.com", "laurie1234"));
        expectedUserList.add(new User(6, "Munif", "Nagi", null, "642-555-0001", "munif@email.com", "Munif1234"));
        expectedAddressList.add(new Address(1, "USA", "NY", "Queens", "11106", "3735 35th st"));
        expectedAddressList.add(new Address(2, "USA", "VA", "Hrendon", "20170", "1817 Forest Drive"));
        expectedOrderList.add(new Order(1,1109.0,3,StringToDate("2022-07-23 00:00:00"),1,1,1,1));
        expectedOrderList.add(new Order(2,1209.0,4,StringToDate("2022-09-22 00:00:00"),2,1,1,2));
    }

    @Test
    public void testReadXML() {
        List<User> userList = xmlParser.readXML(userXMLPath, User.class);
        Assert.assertEquals(userList, expectedUserList);
        List<Address> addressList = xmlParser.readXML(addressXMLPath, Address.class);
        Assert.assertEquals(addressList, expectedAddressList);
        List<Order> orderList = xmlParser.readXML(orderXMLPath, Order.class);
        Assert.assertEquals(orderList, expectedOrderList);
    }
}
