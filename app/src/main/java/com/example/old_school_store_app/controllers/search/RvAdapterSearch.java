package com.example.old_school_store_app.controllers.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.entities.Product;

import java.util.ArrayList;

public class RvAdapterSearch extends RecyclerView.Adapter<RvAdapterSearch.ProductViewHolder>
{
    public static class ProductViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cv;
        public TextView productName;
        public TextView productPrice;
        public ImageView productPicture;

        ProductViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            productName = itemView.findViewById(R.id.product_name);
            productPrice = itemView.findViewById(R.id.product_price);
            productPicture = itemView.findViewById(R.id.product_picture);
        }
    }

    private ArrayList<Product> products;

    public RvAdapterSearch(ArrayList<Product> products)
    {
        this.products = products;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_view, viewGroup, false);
        ProductViewHolder pvh = new ProductViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ProductViewHolder productViewHolder, int i)
    {
        productViewHolder.productName.setText(products.get(i).getName());
        productViewHolder.productPrice.setText(Integer.toString(products.get(i).getPrice())+" руб.");//productViewHolder.productPicture.setImageResource(products.get(i).getMainPicture());
        productViewHolder.productPicture.setImageResource(R.drawable.sonicb11111);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
