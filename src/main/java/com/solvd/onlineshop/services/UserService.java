package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.dao.IAddressDAO;
import com.solvd.onlineshop.dao.IOrderDAO;
import com.solvd.onlineshop.dao.IUserDAO;
import com.solvd.onlineshop.dao.mysql.AddressDAO;
import com.solvd.onlineshop.dao.mysql.OrderDAO;
import com.solvd.onlineshop.dao.mysql.UserDAO;

import java.sql.SQLException;
import java.util.List;

public class UserService implements IUserService{
    private IUserDAO userDAO = new UserDAO();
    private IOrderDAO orderDAO = new OrderDAO();
    private IAddressDAO addressDAO = new AddressDAO();

    public User getUserByID(long id) {
        User u = userDAO.getByID(id);
        if(u==null) {
            throw new RuntimeException("User with id "+ id + " wasn't found!");
        }
        return  u;
    }

    public void createUser(User user) {
        this.userDAO.create(user);
    }

    public List<User> findAllUsers() {
        List<User> usersList = null;
        usersList = userDAO.getAllUsers();
        return usersList;
    }

    public void updateUser(User user) {
        userDAO.update(user);
    }

    public void removeUser(long id) {
        userDAO.remove(id);
    }

}
