package com.acme.shoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.acme.shoppingapp.fragments.OnProductCallback;
import com.acme.shoppingapp.fragments.ShoppingCartFragment;
import com.acme.shoppingapp.model.Product;
import com.acme.shoppingapp.model.ProductList;

public class ShoppingCartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        ShoppingCartFragment shoppingCartFragment = (ShoppingCartFragment) getSupportFragmentManager().findFragmentById(R.id.shoppingCartFragment);
        shoppingCartFragment.setOnProductCallback(new OnProductCallback() {
            @Override
            public void process(Product product) {
                ProductList.INSTANCE.add(0, product);
            }
        });
    }
}
