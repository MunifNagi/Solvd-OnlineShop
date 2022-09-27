package com.solvd.onlineshop.dao;


import com.solvd.onlineshop.entities.Payment;

import java.util.List;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    List<Payment> getPaymentByUserId(long userId);
    List<Payment> getAllPayments();
}
