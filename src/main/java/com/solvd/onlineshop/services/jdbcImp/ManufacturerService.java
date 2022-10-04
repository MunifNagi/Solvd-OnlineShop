package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IManufacturerDAO;
import com.solvd.onlineshop.dao.mysql.ManufacturerDAO;
import com.solvd.onlineshop.entities.Manufacturer;
import com.solvd.onlineshop.services.DAOFacotry;
import com.solvd.onlineshop.services.IManufacturerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ManufacturerService implements IManufacturerService {
    private IManufacturerDAO manufacturerDAO = (IManufacturerDAO) DAOFacotry.create("Manufacturer");
    private static final Logger logger = LogManager.getLogger(ManufacturerService.class);

    @Override
    public Manufacturer getManufacturerById(long id) {
        Manufacturer manufacturer = manufacturerDAO.getByID(id);
        if(manufacturer==null) {
            logger.error("category with id "+ id + " wasn't found!");
        }
        return  manufacturer;
    }

    @Override
    public void createManufacturer(Manufacturer manufacturer) {
        this.manufacturerDAO.create(manufacturer);
    }

    @Override
    public void updateManufacturer(Manufacturer manufacturer) {
        this.manufacturerDAO.update(manufacturer);
    }

    @Override
    public void removeManufacturer(long id) {
        this.manufacturerDAO.remove(id);
    }
}
