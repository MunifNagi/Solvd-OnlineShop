package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IUserService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class UserServiceTest {
    private IUserService userService;
    private User user;

    @BeforeClass
    public void setUp() {
        userService = new com.solvd.onlineshop.services.jdbcImp.UserService();
    }

    @BeforeMethod
    public void createUser() {
        user = new User(100, "Alex", "John", "A", "347-000-0000", "Alex@test.com", "Alex1234");
    }

    @Test
    public void testCreateUser() {
        List<User> users = userService.getAllUsers();
        int numUsers = users.size();
        userService.createUser(user);
        Assert.assertEquals(userService.getAllUsers().size(), numUsers + 1, "Number of users should have been incremented");
    }

    @Test
    public void testGetUserById() {
        User u = userService.getUserByID(100);
        Assert.assertEquals(u.getFirstName(), "Alex", "First name didn't match");
        Assert.assertEquals(u.getLastName(), "John", "Last name didn't match");
        Assert.assertEquals(u.getEmail(), "Alex@test.com", "Email didn't match");
        Assert.assertEquals(u, user, "User selected from database didn't match with expected user");
    }

    @Test
    public void testUpdateUser() {
        User testUser = userService.getUserByID(100);
        testUser.setPhone("3472011122");
        userService.updateUser(testUser);
        User u = userService.getUserByID(100);
        Assert.assertEquals(u.getPhone(), "3472011122", "User phone number was expected to be updated");
        Assert.assertEquals(u, testUser, "User was expected to be updated");
    }


    @AfterClass
    public void destroy() {
        userService.removeUser(100);
    }


}
