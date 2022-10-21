package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Manufacturer;
import com.solvd.onlineshop.services.IManufacturerService;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class ManufacturerServiceTest {
    private IManufacturerService manufacturerService;
    private Manufacturer manufacturer;
    @BeforeClass
    public void setUp() {
        manufacturerService = new com.solvd.onlineshop.services.jdbcImp.ManufacturerService();
        manufacturer = new Manufacturer(1, "Apple","347-200-1000");
    }
    @Test
    public void testCreateManufacturer() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        int manufacturersSize = manufacturers.size();
        manufacturerService.createManufacturer(manufacturer);
        Assert.assertEquals(manufacturerService.getAllManufacturers().size(), manufacturersSize + 1);
    }
    @Test(priority = 1)
    public void testGetManufacturerById() {
        Manufacturer m = manufacturerService.getManufacturerById(1);
        Assert.assertEquals(m.getManufacturerId(), 1);
        Assert.assertEquals(m.getManufacturerName(), "Apple");
        Assert.assertEquals(m.getManufacturerPhone(), "347-200-1000");
        Assert.assertEquals(m, manufacturer);
    }

    @Test(priority = 2)
    public void testUpdateManufacturer() {
        Manufacturer testManufacturer = manufacturerService.getManufacturerById(1);
        testManufacturer.setPhone("646-100-2000");
        manufacturerService.updateManufacturer(testManufacturer);
        Manufacturer m = manufacturerService.getManufacturerById(1);
        Assert.assertEquals(m.getManufacturerPhone(), "646-100-2000");
        Assert.assertEquals(m, testManufacturer);
    }
    @AfterSuite
    public void destroy() {
        manufacturerService.removeManufacturer(1);
    }
}
