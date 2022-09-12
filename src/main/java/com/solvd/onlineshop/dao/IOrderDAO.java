package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Order;

import java.util.List;

public interface IOrderDAO extends IBaseDAO<Order> {
    List<Order> getAllOrders();
    List<Order> getOrderByUserId(long userId);
    List<Order> getOrderByStatusId(long statusId);
}