package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.dao.IAddressDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.services.IAddressService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class AddressService implements IAddressService {
    private static final Logger logger = LogManager.getLogger(AddressService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();

    @Override
    public Address getAddressByID(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            Address address = addressDAO.getByID(id);
            if (address == null) {
                logger.error("Address with id " + id + " wasn't found!");
            }
            return address;
        }
    }

    @Override
    public List<Address> getAllAddresses() {
        try (SqlSession session = sessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            return addressDAO.getAllAddresses();
        }
    }

    @Override
    public void removeAddress(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            try {
                addressDAO.remove(id);
                session.commit();
                logger.info("Address was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing address with id " + id + " wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public void createAddress(Address address) {
        try (SqlSession session = sessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            try {
                addressDAO.create(address);
                session.commit();
                logger.info("Address was inserted Successfully");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                session.rollback();
                logger.error("Inserting Address wasn't successful");
            }
        }

    }

    @Override
    public void updateAddress(Address address) {
        try (SqlSession session = sessionFactory.openSession()) {
            IAddressDAO addressDAO = session.getMapper(IAddressDAO.class);
            try {
                addressDAO.update(address);
                session.commit();
                logger.info("Address was updated Successfully");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                session.rollback();
            }
        }
    }
}
