package com.solvd.onlineshop.entities;

public class Product {
    private long id;
    private String name;
    private double price;
    private String description;
    private long categoryId;
    private double weight;
    private long inStock;

    public Product(long id, String name, double price, String description, long categoryId, double weight, long inStock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.weight = weight;
        this.inStock = inStock;
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


}
