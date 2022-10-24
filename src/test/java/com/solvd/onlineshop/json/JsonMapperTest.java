package com.solvd.onlineshop.json;

import com.solvd.onlineshop.entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;


public class JsonMapperTest extends AbstractJson {
    @Test
    public void testReadJSON() {
        List<User> userList = jsonMapper.readJSON(READ_PATH, User.class);
        Assert.assertEquals(userList, expectedUserList, "Users from JSON must match expected users");
    }

    @Test
    void testWriteJSON() {
        jsonMapper.writeJSON(expectedUserList, WRITE_PATH);
        List<User> userList = jsonMapper.readJSON(WRITE_PATH, User.class);
        Assert.assertEquals(userList, expectedUserList, "Users written to JSON must match expected users");
    }
}
