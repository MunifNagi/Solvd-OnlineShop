package com.solvd.onlineshop.services;


import com.solvd.onlineshop.entities.Manufacturer;

import java.util.List;

public interface IManufacturerService {
    Manufacturer getManufacturerById(long id);

    void createManufacturer(Manufacturer manufacturer);

    void updateManufacturer(Manufacturer manufacturer);

    void removeManufacturer(long id);

    List<Manufacturer> getAllManufacturers();

}
