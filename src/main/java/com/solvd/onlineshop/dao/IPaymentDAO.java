package com.solvd.onlineshop.dao;


import com.solvd.onlineshop.entities.Payment;

import java.util.List;

public interface IPaymentDAO extends IBaseDAO<Payment> {
    Payment getPayemntByUserId(long Userid);
    List<Payment> getAllPayments();
}
