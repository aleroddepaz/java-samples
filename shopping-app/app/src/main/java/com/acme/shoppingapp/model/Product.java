package com.acme.shoppingapp.model;

import java.io.Serializable;

public class Product implements Serializable {

    private final String name;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
