package com.example.old_school_store_app.controllers.catalog;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.entities.Category;
import com.example.old_school_store_app.views.catalog.CategoryItemsFragment;
import com.example.old_school_store_app.views.main.MainActivity;


import java.util.ArrayList;


public class RvAdapterCatalog extends RecyclerView.Adapter<RvAdapterCatalog.CategoryViewHolder>
{
    public static class CategoryViewHolder extends RecyclerView.ViewHolder
    {
        public CardView cvCatalog;
        public TextView categoryName;
        public TextView categoryDescription;
        public ImageView categoryPicture;
        public Button buttonGoToCategory;

        CategoryViewHolder(View itemView)
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
        categoryViewHolder.categoryDescription.setText(categories.get(i).getDescription());
        categoryViewHolder.categoryPicture.setImageResource(categories.get(i).getPictureId());

        categoryViewHolder.buttonGoToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Context context = (Context) DataStorage.Get("context");

                DataStorage.Add("categoryId",categories.get(i).getId());

                //Toast.makeText(context,"AAAAA", Toast.LENGTH_LONG).show();

                CategoryItemsFragment categoryItemsFragment = new CategoryItemsFragment();

                MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

                FragmentTransaction fragmentTransaction;
                fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragmentsContainerMain, categoryItemsFragment);
                fragmentTransaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
