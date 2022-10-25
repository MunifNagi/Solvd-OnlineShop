package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.dao.IShipperDAO;
import com.solvd.onlineshop.entities.Shipper;
import com.solvd.onlineshop.services.IShipperService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShipperService implements IShipperService {

    private static final Logger logger = LogManager.getLogger(ShipperService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();

    @Override
    public Shipper getShipperById(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IShipperDAO shipperDAO = session.getMapper(IShipperDAO.class);
            Shipper shipper = shipperDAO.getByID(id);
            if (shipper == null) {
                logger.error("Shipper with id " + id + " wasn't found!");
            }
            return shipper;
        }
    }

    @Override
    public void createShipper(Shipper shipper) {
        try (SqlSession session = sessionFactory.openSession()) {
            IShipperDAO shipperDAO = session.getMapper(IShipperDAO.class);
            try {
                shipperDAO.create(shipper);
                session.commit();
                logger.info("Shipper was inserted Successfully");
            } catch (Exception e) {
                logger.error("Inserting Shipper wasn't successful", e);
                session.rollback();
            }
        }
    }

    @Override
    public void updateShipper(Shipper shipper) {
        try (SqlSession session = sessionFactory.openSession()) {
            IShipperDAO shipperDAO = session.getMapper(IShipperDAO.class);
            try {
                shipperDAO.update(shipper);
                session.commit();
                logger.info("Shipper was updated Successfully");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                session.rollback();
            }
        }
    }

    @Override
    public void removeShipper(long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            IShipperDAO shipperDAO = session.getMapper(IShipperDAO.class);
            try {
                shipperDAO.remove(id);
                session.commit();
                logger.info("Shipper was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing Shipper with id " + id + " wasn't successful", e);
                session.rollback();
            }
        }
    }
}
