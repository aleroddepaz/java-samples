package com.acme.shoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.acme.shoppingapp.fragments.OnProductCallback;
import com.acme.shoppingapp.fragments.OnProductSwipeListener;
import com.acme.shoppingapp.fragments.ProductsListFragment;
import com.acme.shoppingapp.fragments.ShoppingCartFragment;
import com.acme.shoppingapp.model.Product;
import com.acme.shoppingapp.model.ProductList;
import com.acme.shoppingapp.model.ShoppingCart;

public class MainActivity extends AppCompatActivity {

    private ProductsListFragment productsListFragment;
    private ShoppingCartFragment shoppingCartFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsListFragment = (ProductsListFragment) getSupportFragmentManager().findFragmentById(R.id.productsListFragment);
        shoppingCartFragment = (ShoppingCartFragment) getSupportFragmentManager().findFragmentById(R.id.shoppingCartFragment);

        productsListFragment.setOnProductCallback(new OnProductCallback() {
            @Override
            public void process(Product product) {
                ShoppingCart.INSTANCE.add(0, product);
                if(shoppingCartFragment != null) {
                    shoppingCartFragment.refresh(0);
                }
            }
        });

        if(shoppingCartFragment != null) {
            shoppingCartFragment.setOnProductCallback(new OnProductCallback() {
                @Override
                public void process(Product product) {
                    ProductList.INSTANCE.add(0, product);
                    productsListFragment.refresh(0);
                }
            });
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_shopping_cart) {
            Intent intent = new Intent(this, ShoppingCartActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        productsListFragment.refresh();
    }

}