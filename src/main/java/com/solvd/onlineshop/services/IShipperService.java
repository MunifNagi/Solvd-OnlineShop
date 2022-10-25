package com.solvd.onlineshop.services;

import com.solvd.onlineshop.entities.Shipper;

public interface IShipperService {
    Shipper getShipperById(long id);

    void createShipper(Shipper shipper);

    void updateShipper(Shipper shipper);

    void removeShipper(long id);
}
