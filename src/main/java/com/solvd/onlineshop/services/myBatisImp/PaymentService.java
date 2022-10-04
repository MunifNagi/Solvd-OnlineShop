package com.solvd.onlineshop.services.myBatisImp;

import com.solvd.onlineshop.dao.IAddressDAO;
import com.solvd.onlineshop.dao.IPaymentDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Payment;
import com.solvd.onlineshop.services.IPaymentService;
import com.solvd.onlineshop.services.MyBatisFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class PaymentService implements IPaymentService {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private final static SqlSessionFactory sessionFactory = MyBatisFactory.getSessionFactory();
    @Override
    public Payment getPaymentByID(long id) {
        try(SqlSession session = sessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            Payment payment = paymentDAO.getByID(id);
            if(payment==null) {
                logger.error("Payment with id " + id + " wasn't found!");
            }
            return payment;
        }
    }

    @Override
    public List<Payment> getAllPayments() {
        try(SqlSession session = sessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            return paymentDAO.getAllPayments();
        }
    }

    @Override
    public void removePayment(long id) {
        try( SqlSession session = sessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            try {
                paymentDAO.remove(id);
                session.commit();
                logger.info("Payment was removed Successfully");
            } catch (Exception e) {
                logger.error("Removing payment with id " + id + " wasn't successful",e);
                session.rollback();
            }
        }
    }

    @Override
    public void createPayment(Payment payment) {
        try( SqlSession session = sessionFactory.openSession()){
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            try {
                paymentDAO.create(payment);
                session.commit();
                logger.info("Payment was inserted Successfully");
            } catch (Exception e) {
                logger.error("Inserting Payment wasn't successful",e);
                session.rollback();
            }
        }
    }

    @Override
    public void updatePayment(Payment payment) {
        try( SqlSession session = sessionFactory.openSession()){
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            try {
                paymentDAO.update(payment);
                session.commit();
                logger.info("payment was updated Successfully");
            } catch (Exception e) {
                logger.error(e.getMessage(),e);
                session.rollback();
            }
        }
    }

    @Override
    public List<Payment> getPaymentsByUserId(long userId) {
        try(SqlSession session = sessionFactory.openSession()) {
            IPaymentDAO paymentDAO = session.getMapper(IPaymentDAO.class);
            List<Payment> payments = paymentDAO.getPaymentByUserId(userId);
            if(payments==null) {
                logger.error("No Payments with user id " + userId + " were found!");
            }
            return payments;
        }
    }
}
