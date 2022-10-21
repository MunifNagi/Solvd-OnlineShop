package com.solvd.onlineshop.json;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Payment;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IParseXML;
import com.solvd.onlineshop.services.JsonMapper;
import org.testng.annotations.AfterSuite;

import java.util.ArrayList;
import java.util.List;

public class AbstractJson {
    protected JsonMapper jsonMapper = new JsonMapper();
    protected String userJSONPath = "src/main/resources/json/user.json";
    protected List<User> expectedUserList = new ArrayList<User>();
    @AfterSuite
    public void destroy() {
        expectedUserList.clear();
    }
}
