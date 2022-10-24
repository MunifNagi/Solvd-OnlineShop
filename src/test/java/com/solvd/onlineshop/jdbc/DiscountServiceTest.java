package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Discount;
import com.solvd.onlineshop.services.IDiscountService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class DiscountServiceTest {
    private IDiscountService discountService;
    private Discount discount;

    @BeforeClass
    public void setUp() {
        discountService = new com.solvd.onlineshop.services.jdbcImp.DiscountService();
    }

    @BeforeMethod
    public void createDiscount() {
        discount = new Discount(2, "25% off", 0.25);
    }

    @Test
    public void testCreateDiscount() {
        List<Discount> discountLis = discountService.getAllDiscounts();
        int discountSize = discountLis.size();
        discountService.createDiscount(discount);
        Assert.assertEquals(discountService.getAllDiscounts().size(), discountSize + 1, "Number of discounts should have been incremented after insertion");
    }

    @Test
    public void testGetDiscountById() {
        Discount d = discountService.getDiscountByID(2);
        Assert.assertEquals(d.getDiscountId(), 2, "Discount id didn't match");
        Assert.assertEquals(d.getName(), "25% off", "Discount name didn't match");
        Assert.assertEquals(d.getPercentage(), 0.25, "Discount percentage didn't match");
        Assert.assertEquals(d, discount, "Discount selected from database didn't match with expected Discount");
    }

    @Test(priority = 2)
    public void testUpdateDiscount() {
        Discount testDiscount = discountService.getDiscountByID(2);
        testDiscount.setName("30% off");
        testDiscount.setPercentage(0.30);
        discountService.updateDiscount(testDiscount);
        Discount d = discountService.getDiscountByID(2);
        Assert.assertEquals(d.getPercentage(), 0.30, "Discount amount was expected to be updated");
        Assert.assertEquals(d.getName(), "30% off", "Discount name  was expected to be updated");
        Assert.assertEquals(d, testDiscount, "Discount was expected to be updated");
    }

    @AfterClass
    public void destroy() {
        discountService.removeDiscount(2);
    }
}
