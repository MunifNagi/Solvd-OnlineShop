package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Discount;
import com.solvd.onlineshop.services.IDiscountService;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DiscountServiceTest {
    private IDiscountService discountService;
    private Discount discount;
    @BeforeClass
    public void setUp() {
        discountService = new com.solvd.onlineshop.services.jdbcImp.DiscountService();
        discount = new Discount(1, "25% off",0.25);
    }
    @Test
    public void testCreateDiscount() {
        List<Discount> discountLis = discountService.getAllDiscounts();
        int discountSize = discountLis.size();
        discountService.createDiscount(discount);
        Assert.assertEquals(discountService.getAllDiscounts().size(), discountSize + 1);
    }
    @Test(priority = 1)
    public void testGetDiscountById() {
        Discount d = discountService.getDiscountByID(1);
        Assert.assertEquals(d.getDiscountId(), 1);
        Assert.assertEquals(d.getName(), "25% off");
        Assert.assertEquals(d.getPercentage(), 0.25);
        Assert.assertEquals(d, discount);
    }

    @Test(priority = 2)
    public void testUpdateDiscount() {
        Discount testDiscount = discountService.getDiscountByID(1);
        testDiscount.setName("30% off");
        testDiscount.setPercentage(0.30);
        discountService.updateDiscount(testDiscount);
        Discount d = discountService.getDiscountByID(1);
        Assert.assertEquals(d.getPercentage(), 0.30);
        Assert.assertEquals(d.getName(), "30% off");
        Assert.assertEquals(d, testDiscount);
    }
    @AfterSuite
    public void destroy() {
        discountService.removeDiscount(1);
    }
}
