package com.acme.shoppingapp.model;

import java.util.ArrayList;
import java.util.List;

public enum ProductList {
    INSTANCE;

    private final List<Product> products = new ArrayList<>();

    ProductList() {
        products.add(new Product("Bike"));
        products.add(new Product("Electric Guitar"));
        products.add(new Product("Skateboard"));
    }

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
