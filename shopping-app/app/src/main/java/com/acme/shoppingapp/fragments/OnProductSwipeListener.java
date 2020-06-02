package com.acme.shoppingapp.fragments;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;

import com.acme.shoppingapp.model.Product;
import com.acme.shoppingapp.model.ProductList;
import com.acme.shoppingapp.model.ShoppingCart;

public abstract class OnProductSwipeListener extends ItemTouchHelper.Callback {

    private final int direction;

    protected OnProductSwipeListener(int direction) {
        this.direction = direction;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0, direction);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (direction == this.direction) {
            this.onSwiped(viewHolder.getAdapterPosition());

            Log.d("MYTAG", "Contenido de lista de productos:");
            for (Product product : ProductList.INSTANCE.getProducts()) {
                Log.d("MYTAG", "\t" + product.toString());
            }
            Log.d("MYTAG", "Contenido de carrito:");
            for (Product product : ShoppingCart.INSTANCE.getProducts()) {
                Log.d("MYTAG", "\t" + product.toString());
            }
        }
    }

    abstract void onSwiped(int position);

}