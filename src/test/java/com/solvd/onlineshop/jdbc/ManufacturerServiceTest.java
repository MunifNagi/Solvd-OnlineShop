package com.solvd.onlineshop.jdbc;

import com.solvd.onlineshop.entities.Manufacturer;
import com.solvd.onlineshop.services.IManufacturerService;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class ManufacturerServiceTest {
    private IManufacturerService manufacturerService;
    private Manufacturer manufacturer;

    @BeforeClass
    public void setUp() {
        manufacturerService = new com.solvd.onlineshop.services.jdbcImp.ManufacturerService();
    }

    @BeforeMethod
    public void createManufacturer() {
        manufacturer = new Manufacturer(2, "Apple", "347-200-1000");
    }

    @Test
    public void testCreateManufacturer() {
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        int manufacturersSize = manufacturers.size();
        manufacturerService.createManufacturer(manufacturer);
        Assert.assertEquals(manufacturerService.getAllManufacturers().size(), manufacturersSize + 1, "Number of manufacturers should have been incremented after insertion");
    }

    @Test
    public void testGetManufacturerById() {
        Manufacturer m = manufacturerService.getManufacturerById(2);
        Assert.assertEquals(m.getManufacturerId(), 2, "Manufacturer ids didn't match");
        Assert.assertEquals(m.getManufacturerName(), "Apple", "Manufacturer name didn't match");
        Assert.assertEquals(m.getManufacturerPhone(), "347-200-1000", "Manufacturer phone didn't match");
        Assert.assertEquals(m, manufacturer, "Manufacturer selected from database didn't match with expected Manufacturer");
    }

    @Test
    public void testUpdateManufacturer() {
        Manufacturer testManufacturer = manufacturerService.getManufacturerById(2);
        testManufacturer.setPhone("646-100-2000");
        manufacturerService.updateManufacturer(testManufacturer);
        Manufacturer m = manufacturerService.getManufacturerById(2);
        Assert.assertEquals(m.getManufacturerPhone(), "646-100-2000", "Manufacturer phone number was expected to be updated");
        Assert.assertEquals(m, testManufacturer, "Manufacturer was expected to be updated");
    }

    @AfterClass
    public void destroy() {
        manufacturerService.removeManufacturer(2);
    }
}
