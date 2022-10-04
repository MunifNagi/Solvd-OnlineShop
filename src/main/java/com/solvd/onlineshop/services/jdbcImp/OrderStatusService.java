package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IOrderStatusDAO;
import com.solvd.onlineshop.dao.mysql.OrderStatusDAO;
import com.solvd.onlineshop.entities.OrderStatus;
import com.solvd.onlineshop.services.IOrderStatusService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OrderStatusService implements IOrderStatusService {
    private IOrderStatusDAO orderStatusDAO = new OrderStatusDAO();
    private static final Logger logger = LogManager.getLogger(OrderStatusService.class);

    @Override
    public OrderStatus getOrderStatusById(long id) {
        OrderStatus orderStatus = orderStatusDAO.getByID(id);
        if(orderStatus==null) {
            logger.error("Status with id "+ id + " wasn't found!");
        }
        return  orderStatus;
    }

    @Override
    public void createOrderStatus(OrderStatus orderStatus) {
        this.orderStatusDAO.create(orderStatus);
    }

    @Override
    public void updateOrderStatus(OrderStatus orderStatus) {
        this.orderStatusDAO.update(orderStatus);
    }

    @Override
    public void removeOrderStatus(long id) {
        this.orderStatusDAO.remove(id);
    }

}
