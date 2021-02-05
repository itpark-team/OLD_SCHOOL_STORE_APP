package com.example.old_school_store_app.controllers.cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.entities.CartItem;

import java.util.ArrayList;

public class RvAdapterCart extends RecyclerView.Adapter<RvAdapterCart.CartViewHolder>
{
    public static class CartViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView productCartPicture;
        public TextView productCartName;
        public TextView productCartPrice;

        CartViewHolder(View itemView)
        {
            super(itemView);

            productCartPicture = itemView.findViewById(R.id.productCartPicture);
            productCartName = itemView.findViewById(R.id.productCartName);
            productCartPrice = itemView.findViewById(R.id.productCartPrice);
        }
    }

    private ArrayList<CartItem> userProducts;

    public RvAdapterCart (ArrayList<CartItem> userProducts)
    {
        this.userProducts = userProducts;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RvAdapterCart.CartViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cart_item_view, viewGroup, false);
        RvAdapterCart.CartViewHolder cvh = new RvAdapterCart.CartViewHolder(v);
        return cvh;
    }

    @Override
    public int getItemCount() {
        return userProducts.size();
    }

    @Override
    public void onBindViewHolder(RvAdapterCart.CartViewHolder cartViewHolder, int i)
    {
        cartViewHolder.productCartPicture.setImageResource(userProducts.get(i).getProduct().getMainPictureId());

        cartViewHolder.productCartName.setText(userProducts.get(i).getProduct().getName());
        cartViewHolder.productCartPrice.setText(Integer.toString(userProducts.get(i).getProduct().getPrice())+" руб.");
    }
}
