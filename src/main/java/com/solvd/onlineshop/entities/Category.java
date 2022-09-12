package com.solvd.onlineshop.entities;

public class Category {
    private long id;
    private String name;

    public Category(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getCategoryId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
