package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.User;

import java.util.List;

public interface IUserService {
    User getUserByID(long id);
    void createUser(User user);
    void updateUser(User user);
    void removeUser(long id);
    List<User> getAllUsers();


}
