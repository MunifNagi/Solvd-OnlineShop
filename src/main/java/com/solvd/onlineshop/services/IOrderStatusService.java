package com.solvd.onlineshop.services;


import com.solvd.onlineshop.entities.OrderStatus;

public interface IOrderStatusService {
    OrderStatus getOrderStatusById(long id);

    void createOrderStatus(OrderStatus orderStatus);

    void updateOrderStatus(OrderStatus orderStatus);

    void removeOrderStatus(long id);
}
