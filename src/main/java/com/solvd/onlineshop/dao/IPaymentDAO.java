package com.solvd.onlineshop.dao;


import com.solvd.onlineshop.entities.Payment;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    Payment getPayemntByUserId(long id);
}
