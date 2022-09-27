package com.solvd.onlineshop.services;

import com.solvd.onlineshop.dao.IAddressDAO;
import com.solvd.onlineshop.dao.IPaymentDAO;
import com.solvd.onlineshop.dao.mysql.AddressDAO;
import com.solvd.onlineshop.dao.mysql.PaymentDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.entities.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class PaymentService implements IPaymentService{
    private IPaymentDAO paymentDAO = new PaymentDAO();
    private static final Logger logger = LogManager.getLogger(AddressService.class);
    @Override
    public Payment getPaymentByID(long id) {
        Payment payment = paymentDAO.getByID(id);
        if(payment==null) {
            logger.error("Payment with id "+ id + " wasn't found!");
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList = paymentDAO.getAllPayments();
        return paymentList;
    }

    @Override
    public void removePayment(long id) {
        paymentDAO.remove(id);
    }

    @Override
    public void createPayment(Payment payment) {
        paymentDAO.create(payment);
    }

    @Override
    public void updatePayment(Payment payment) {
        paymentDAO.update(payment);
    }
}
