package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IAddressDAO;
import com.solvd.onlineshop.dao.mysql.AddressDAO;
import com.solvd.onlineshop.entities.Address;
import com.solvd.onlineshop.services.IAddressService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class AddressService implements IAddressService {
    private IAddressDAO addressDAO = new AddressDAO();
    private static final Logger logger = LogManager.getLogger(AddressService.class);

    @Override
    public Address getAddressByID(long id) {
        Address address = addressDAO.getByID(id);
        if(address==null) {
            logger.error("Address with id "+ id + " wasn't found!");
        }
        return address;
    }

    @Override
    public List<Address> getAllAddresses() {
        List<Address> addressList = new ArrayList<>();
        addressList = addressDAO.getAllAddresses();
        return addressList;
    }

    @Override
    public void removeAddress(long id) {
        addressDAO.remove(id);

    }

    @Override
    public void createAddress(Address address) {
        addressDAO.create(address);
    }

    @Override
    public void updateAddress(Address address) {
        addressDAO.update(address);
    }
}
