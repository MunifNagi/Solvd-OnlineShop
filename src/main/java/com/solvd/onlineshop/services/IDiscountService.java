package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Discount;

import java.util.List;

public interface IDiscountService {
    Discount getDiscountByID(long id);
    void createDiscount(Discount discount);
    void updateDiscount(Discount discount);
    void removeDiscount(long id);
    List<Discount> getAllDiscounts();
}
