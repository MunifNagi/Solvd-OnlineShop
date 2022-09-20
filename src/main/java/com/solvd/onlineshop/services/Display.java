package com.solvd.onlineshop.services;

import java.util.Iterator;
import java.util.List;

public abstract class Display {
    public static void print(List<?> objects) {
        objects.stream().forEach(iter -> System.out.println(iter));
    }

}
