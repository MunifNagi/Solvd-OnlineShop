package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IOrderDAO;
import com.solvd.onlineshop.dao.mysql.OrderDAO;
import com.solvd.onlineshop.entities.Order;
import com.solvd.onlineshop.services.IOrderService;

import java.util.List;

public class OrderService implements IOrderService {
    private IOrderDAO orderDAO = new OrderDAO();

    @Override
    public Order getOrderByID(long id) {
        Order order = orderDAO.getByID(id);
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = null;
        orderList = orderDAO.getAllOrders();
        return orderList;
    }

    @Override
    public void removeOrder(long id) {
        orderDAO.remove(id);

    }

    @Override
    public void createOrder(Order order) {
        orderDAO.create(order);

    }

    @Override
    public void updateOrder(Order order) {
        orderDAO.update(order);
    }

}
