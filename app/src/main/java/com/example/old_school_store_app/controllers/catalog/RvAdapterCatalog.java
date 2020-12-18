package com.example.old_school_store_app.controllers.catalog;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.entities.Category;
import com.example.old_school_store_app.views.catalog.CatalogItemsFragment;
import com.example.old_school_store_app.views.main.MainActivity;


import java.util.ArrayList;


public class RvAdapterCatalog extends RecyclerView.Adapter<RvAdapterCatalog.CatalogViewHolder>
{
    public static class CatalogViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cvCatalog;
        public TextView categoryName;
        public TextView categoryDescription;
        public ImageView categoryPicture;
        public Button buttonGoToCategory;

        CatalogViewHolder(View itemView)
        {
            super(itemView);
            cvCatalog = itemView.findViewById(R.id.cvCatalog);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryDescription = itemView.findViewById(R.id.categoryDescription);
            categoryPicture = itemView.findViewById(R.id.categoryPicture);
            buttonGoToCategory = itemView.findViewById(R.id.buttonGoToCategory);
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
    public CatalogViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.catalog_item_view, viewGroup, false);
        CatalogViewHolder cvh = new CatalogViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(CatalogViewHolder catalogViewHolder, int i)
    {
        catalogViewHolder.categoryName.setText(categories.get(i).getName());
        catalogViewHolder.categoryDescription.setText(categories.get(i).getDescription());
        catalogViewHolder.categoryPicture.setImageResource(categories.get(i).getPictureId());

        catalogViewHolder.buttonGoToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DataStorage.Add("categoryId",categories.get(i).getId());

                CatalogItemsFragment catalogItemsFragment = new CatalogItemsFragment();

                MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogItemsFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
