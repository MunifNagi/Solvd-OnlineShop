package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.User;

import java.util.List;

public interface IUserDAO extends IBaseDAO<User> {
    List<User> getAllUsers();
}
