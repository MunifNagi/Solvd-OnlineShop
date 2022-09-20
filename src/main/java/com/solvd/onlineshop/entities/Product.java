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
    public Product() {

    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", inStock=" + inStock +
                '}';
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

    public void setProductId(long id) {
        this.id = id;
    }

    public void setProductName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setInStock(long inStock) {
        this.inStock = inStock;
    }

    public void setDiscountId(long discountId) {
        this.discountId = discountId;
    }

    public void setManufacturerId(long manufacturerId) {
        this.manufacturerId = manufacturerId;
    }
}
