package com.solvd.onlineshop.entities;

public class Product {
    private long id;
    private String name;
    private double price;
    private String description;
    private long categoryId;
    private double weight;
    private long inStock;
    private long discountId;
    private long manufacturerId;

    public Product(long id, String name, double price, String description, long categoryId, double weight, long inStock, long discountId, long manufacturerId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.weight = weight;
        this.inStock = inStock;
        this.discountId = discountId;
        this.manufacturerId = manufacturerId;
    }

    public long getProductId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public double getWeight() {
        return weight;
    }

    public long getInStock() {
        return inStock;
    }

    public long getDiscountId() {
        return discountId;
    }

    public long getManufacturerId() {
        return manufacturerId;
    }
}
