package com.solvd.onlineshop.xml;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.JAXBHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.onlineshop.services.DateAdaptor.StringToDate;

public class JAXBTest extends AbstractXML {

    @BeforeClass
    public void setUp() {
        xmlParser = new JAXBHandler();
    }

    @Test
    public void testReadXML() {
        List<User> userList = xmlParser.readXML(USER_XML_PATH, User.class);
        Assert.assertEquals(userList, expectedUserList);
        List<Address> addressList = xmlParser.readXML(ADDRESS_XML_PATH, Address.class);
        Assert.assertEquals(addressList, expectedAddressList);
        List<Order> orderList = xmlParser.readXML(ORDER_XML_PATH, Order.class);
        Assert.assertEquals(orderList, expectedOrderList);
    }

    @Test
    void testWriteXML() {
        JAXBHandler jaxbHandler = new JAXBHandler();
        jaxbHandler.writeXML(expectedUserList, User.class, "src/main/resources/xml/new-user.xml");
        List<User> userList = jaxbHandler.readXML("src/main/resources/xml/new-user.xml", User.class);
        Assert.assertEquals(userList, expectedUserList);
    }

}
