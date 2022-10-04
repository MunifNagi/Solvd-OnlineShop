package com.solvd.onlineshop.services;

import com.solvd.onlineshop.dao.mysql.*;

public class DAOFacotry {
    public static MySQLDAO create(String type) {
        switch (type) {
            case "Address":
                return new AddressDAO();
            case "User":
                return new UserDAO();
            case "UserAddress":
                return new UserAddressDAO();
            case "Order":
                return new OrderDAO();
            case "OrderStatus":
                return new OrderStatusDAO();
            case "Payment":
                return new PaymentDAO();
            case "Product":
                return new ProductDAO();
            case "Rating":
                return new RatingDAO();
            case "Report":
                return new ReportDAO();
            case "Category":
                return new CategoryDAO();
            case "Manufacturer":
                return new ManufacturerDAO();
            case "Cart":
                return new CartDAO();
            case "Discount":
                return new DiscountDAO();
            case "Shipment":
                return new ShipmentDAO();
            case "Shipper":
                return new ShipperDAO();
            case "Return":
                return new ReturnDAO();
        }
        return null;
    }
}
