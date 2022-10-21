package com.solvd.onlineshop.xml;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IParseXML;
import org.testng.annotations.AfterSuite;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractXML {
    protected IParseXML xmlParser;
    protected String userXMLPath;
    protected String orderXMLPath;
    protected String addressXMLPath;
    protected List<User> expectedUserList = new ArrayList<User>();
    protected List<Order> expectedOrderList = new ArrayList<Order>();
    protected List<Address> expectedAddressList = new ArrayList<Address>();


    @AfterSuite
    public void destroy() {
        expectedUserList.clear();
        expectedAddressList.clear();
        expectedOrderList.clear();
    }
}
