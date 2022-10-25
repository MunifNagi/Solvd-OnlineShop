package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Payment;

import java.util.List;

public interface IPaymentService {
    Payment getPaymentByID(long id);

    List<Payment> getAllPayments();

    void removePayment(long id);

    void createPayment(Payment payment);

    void updatePayment(Payment payment);

    List<Payment> getPaymentsByUserId(long userID);
}
