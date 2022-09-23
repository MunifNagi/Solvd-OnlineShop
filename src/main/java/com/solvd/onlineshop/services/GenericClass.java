package com.solvd.onlineshop.services;

/** To get generic type
 * of class at runtime
 */

public class GenericClass<T> {
    private final Class<T> type;

    public GenericClass(Class<T> type) {
        this.type = type;
    }

    public Class<T> getType() {
        return this.type;
    }
}
