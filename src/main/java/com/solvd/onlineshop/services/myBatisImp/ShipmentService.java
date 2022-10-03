package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.dao.IShipmentDAO;
import com.solvd.onlineshop.entities.Shipment;
import com.solvd.onlineshop.services.IShipmentService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ShipmentService implements IShipmentService {
    private static final Logger logger = LogManager.getLogger(ShipmentService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();

    @Override
    public Shipment getShipmentById(long id) {
        try(SqlSession session = sessionFactory.openSession()) {
            IShipmentDAO shipmentDAO = session.getMapper(IShipmentDAO.class);
            Shipment shipment = shipmentDAO.getByID(id);
            if(shipment==null) {
                logger.error("Shipment with id " + id + " wasn't found!");
            }
            return shipment;
        }
    }

    @Override
    public void createShipment(Shipment shipment) {
        try( SqlSession session = sessionFactory.openSession()){
            IShipmentDAO shipmentDAO = session.getMapper(IShipmentDAO.class);
            try {
                shipmentDAO.create(shipment);
                session.commit();
                logger.info("shipment was inserted Successfully");
            } catch (Exception e) {
                logger.error("Inserting shipment wasn't successful",e);
                session.rollback();
            }
        }
    }

    @Override
    public void updateShipment(Shipment shipment) {
        try( SqlSession session = sessionFactory.openSession()){
            IShipmentDAO shipmentDAO = session.getMapper(IShipmentDAO.class);
            try {
                shipmentDAO.update(shipment);
                session.commit();
                logger.info("Shipment was updated Successfully");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                session.rollback();
            }
        }
    }

    @Override
    public void removeShipment(long id) {
        try( SqlSession session = sessionFactory.openSession()) {
            IShipmentDAO shipmentDAO = session.getMapper(IShipmentDAO.class);
            try {
                shipmentDAO.remove(id);
                session.commit();
                logger.info("Shipment was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing Shipment with id " + id + " wasn't successful",e);
                session.rollback();
            }
        }
    }
}
