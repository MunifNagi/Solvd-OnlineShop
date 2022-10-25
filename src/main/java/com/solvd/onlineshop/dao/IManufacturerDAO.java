package com.solvd.onlineshop.dao;

import com.solvd.onlineshop.entities.Manufacturer;

import java.util.List;

public interface IManufacturerDAO extends IBaseDAO<Manufacturer>{
    List<Manufacturer> getAllManufacturers();
}
