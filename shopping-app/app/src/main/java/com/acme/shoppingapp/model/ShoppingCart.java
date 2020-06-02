package com.acme.shoppingapp.model;

import com.acme.shoppingapp.model.Product;

import java.util.ArrayList;
import java.util.List;

public enum ShoppingCart {
    INSTANCE;

    private final List<Product> products = new ArrayList<>();

    public void add(int position, Product product) {
        products.add(position, product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product remove(int position){
        return products.remove(position);
    }
}
