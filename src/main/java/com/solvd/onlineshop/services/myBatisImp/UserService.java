package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.Main;
import com.solvd.onlineshop.dao.IUserDAO;
import com.solvd.onlineshop.dao.mysql.UserDAO;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IUserService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserService implements IUserService {
    private static final Logger logger = LogManager.getLogger(UserService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();

    @Override
    public User getUserByID(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            User u = userDAO.getByID(id);
            if (u == null) {
                logger.error("User with id " + id + " wasn't found!");
            }
            logger.info("User was removed Successfully");
            return u;
        }
    }

    @Override
    public void createUser(User user) {
        try (SqlSession session = sessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            try {
                userDAO.create(user);
                session.commit();
                logger.info("User was inserted Successfully");
            } catch (Exception e) {
                logger.error("Inserting user wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try (SqlSession session = sessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            try {
                userDAO.update(user);
                session.commit();
                logger.info("User was updated Successfully");
            } catch (Exception e) {
                logger.error("updating user wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public void removeUser(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            try {
                userDAO.remove(id);
                session.commit();
                logger.info("User was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing user with id " + id + " wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SqlSession session = sessionFactory.openSession()) {
            IUserDAO userDAO = session.getMapper(IUserDAO.class);
            return userDAO.getAllUsers();
        }

    }
}
