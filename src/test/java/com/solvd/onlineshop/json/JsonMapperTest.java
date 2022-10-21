package com.solvd.onlineshop.json;

import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.entities.Payment;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.JAXBHandler;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static com.solvd.onlineshop.services.DateAdaptor.StringToDate;

public class JsonMapperTest extends AbstractJson {
    @BeforeClass
    public void setUp() {
        expectedUserList.add(new User(1, "Tom", "Alex", null, "347-200-0000", "tom@email.com", "tom1234"));
        expectedUserList.add(new User(2, "Maria", "Hernandez", null, "347-222-0001", "maria@email.com", "maria1234"));
        expectedUserList.add(new User(3, "Marta", "Tsyndra", null, "347-222-1111", "marta@email.com", "marta1234"));
    }
    @Test
    public void testReadJSON() {
        List<User> userList = jsonMapper.readJSON(userJSONPath, User.class);
        Assert.assertEquals(userList, expectedUserList);
    }
    @Test(priority = 1)
    void testWriteJSON() {
        jsonMapper.writeJSON(expectedUserList,  "src/main/resources/json/new-user.json");
        List<User> userList = jsonMapper.readJSON("src/main/resources/json/new-user.json", User.class);
        Assert.assertEquals(userList, expectedUserList);
    }
}
