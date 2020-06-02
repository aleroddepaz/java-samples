package com.acme.shoppingapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.acme.shoppingapp.R;
import com.acme.shoppingapp.adapters.ProductAdapter;
import com.acme.shoppingapp.model.Product;
import com.acme.shoppingapp.model.ProductList;
import com.acme.shoppingapp.model.ShoppingCart;

public class ProductsListFragment extends Fragment {

    private final ProductAdapter adapter = new ProductAdapter(ProductList.INSTANCE.getProducts());

    private OnProductCallback callback;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);

        TextView tvTitle = (TextView) view.findViewById(R.id.fragmentTitle);
        tvTitle.setText("Product list");

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.productListView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new OnProductSwipeListener(ItemTouchHelper.RIGHT) {
            @Override
            void onSwiped(int position) {
                Product product = ProductList.INSTANCE.remove(position);
                adapter.notifyItemRemoved(position);
                if(callback != null) {
                    callback.process(product);
                }
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
        return view;
    }

    public void refresh(int position) {
        adapter.notifyItemInserted(position);
    }

    public void refresh() {
        adapter.notifyDataSetChanged();
    }

    public void setOnProductCallback(OnProductCallback callback){
        this.callback = callback;
    }

}