package com.solvd.onlineshop.myBatis;

import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IUserService;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class UserServiceTest {
    private IUserService userService;
    private User user;

    @BeforeClass
    public void setUp() {
        userService = new com.solvd.onlineshop.services.myBatisImp.UserService();
        user = new User(100,"Alex","John","A","347-000-0000","Alex@test.com","Alex1234");

    }

    @Test
    public void testCreateUser() {
        List<User> users = userService.getAllUsers();
        int numUsers = users.size();
        userService.createUser(user);
        Assert.assertEquals(userService.getAllUsers().size(),numUsers+1);
    }

    @Test(priority = 1)
    public void testGetUserById() {
        User u = userService.getUserByID(100);
        Assert.assertEquals(u.getFirstName(),"Alex");
        Assert.assertEquals(u.getLastName(), "John");
        Assert.assertEquals(u.getEmail(), "Alex@test.com");
        Assert.assertEquals(u, user);
    }

    @Test(priority = 2)
    public void testUpdateUser() {
        User testUser = userService.getUserByID(100);
        testUser.setPhone("3472011122");
        userService.updateUser(testUser);
        User u = userService.getUserByID(100);
        Assert.assertEquals(u.getPhone(),"3472011122");
        Assert.assertEquals(u, testUser);
    }

    @AfterClass
    public void destroy() {
        userService.removeUser(100);
    }
}
