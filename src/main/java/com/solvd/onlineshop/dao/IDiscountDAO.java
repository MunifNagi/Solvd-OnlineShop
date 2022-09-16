package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Category;
import com.solvd.onlineshop.entities.Discount;

import java.util.List;

public interface IDiscountDAO extends IBaseDAO<Discount> {
    List<Discount> getAllDiscounts();
}
