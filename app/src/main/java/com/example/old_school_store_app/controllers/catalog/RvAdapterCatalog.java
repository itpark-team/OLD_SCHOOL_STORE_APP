package com.example.old_school_store_app.controllers.catalog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.entities.Category;


import java.util.ArrayList;


public class RvAdapterCatalog extends RecyclerView.Adapter<RvAdapterCatalog.CategoryViewHolder>
{
    public static class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cvCatalog;
        public TextView categoryName;
        public ImageView categoryPicture;

        CategoryViewHolder(View itemView)
        {
            super(itemView);
            cvCatalog = itemView.findViewById(R.id.cvCatalog);
            categoryName = itemView.findViewById(R.id.category_name);
            categoryPicture = itemView.findViewById(R.id.category_picture);
        }
    }

    private ArrayList<Category> categories;

    public RvAdapterCatalog(ArrayList<Category> categories)
    {
        this.categories = categories;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public RvAdapterCatalog.CategoryViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item_view, viewGroup, false);
        RvAdapterCatalog.CategoryViewHolder cvh = new RvAdapterCatalog.CategoryViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(RvAdapterCatalog.CategoryViewHolder categoryViewHolder, int i)
    {
        categoryViewHolder.categoryName.setText(categories.get(i).getName());
        categoryViewHolder.categoryPicture.setImageResource(categories.get(i).getPictureId());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
