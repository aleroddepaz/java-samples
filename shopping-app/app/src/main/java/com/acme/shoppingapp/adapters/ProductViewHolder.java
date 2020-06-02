package com.acme.shoppingapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.acme.shoppingapp.model.Product;
import com.acme.shoppingapp.R;

class ProductViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvNombre;

    public ProductViewHolder(View itemView) {
        super(itemView);
        this.tvNombre = itemView.findViewById(R.id.tvNombre);
    }

    public void bind(Product product) {
        tvNombre.setText(product.getName());
    }

}