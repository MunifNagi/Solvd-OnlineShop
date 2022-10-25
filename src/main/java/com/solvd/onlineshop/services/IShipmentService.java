package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Shipment;

public interface IShipmentService {
    Shipment getShipmentById(long id);

    void createShipment(Shipment shipment);

    void updateShipment(Shipment shipment);

    void removeShipment(long id);
}
