package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Order;

import java.util.List;

public interface IOrderService {
    Order getOrderByID(long id);

    List<Order> getAllOrders();

    void removeOrder(long id);

    void createOrder(Order order);

    void updateOrder(Order order);
}
