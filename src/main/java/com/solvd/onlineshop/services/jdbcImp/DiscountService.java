package com.solvd.onlineshop.services.jdbcImp;

import com.solvd.onlineshop.dao.IDiscountDAO;
import com.solvd.onlineshop.dao.mysql.DiscountDAO;
import com.solvd.onlineshop.entities.Discount;
import com.solvd.onlineshop.entities.User;
import com.solvd.onlineshop.services.IDiscountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DiscountService implements IDiscountService {
    private IDiscountDAO discountDAO = new DiscountDAO();

    private static final Logger logger = LogManager.getLogger(DiscountService.class);

    @Override
    public Discount getDiscountByID(long id) {
        Discount discount = discountDAO.getByID(id);
        if(discount==null) {
            logger.error("Discount with id "+ id + " wasn't found!");
        }
        return  discount;
    }

    @Override
    public void createDiscount(Discount discount) {
        this.discountDAO.create(discount);
    }

    @Override
    public void updateDiscount(Discount discount) {
        this.discountDAO.update(discount);
    }

    @Override
    public void removeDiscount(long id) {
        this.discountDAO.remove(id);
    }

    @Override
    public List<Discount> getAllDiscounts() {
        List<Discount> discounts = null;
        discounts = discountDAO.getAllDiscounts();
        return discounts;
    }
}
