package com.example.old_school_store_app.controllers.catalog;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Category;


import java.util.ArrayList;

public class ControllerCatalogFragment
{
    private View view;
    private DbManager db;

    public ControllerCatalogFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void ShowAllCategories()
    {
        ArrayList<Category> allCategories = db.GetTableCategories().GetAll();

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < allCategories.size(); i++)
        {
            Category category = allCategories.get(i);

            int pictureId = context.getResources().getIdentifier(category.getPicturePath(),"drawable", context.getPackageName());

            category.setPictureId(pictureId);
        }

        RecyclerView recyclerViewCatalog = view.findViewById(R.id.recyclerViewCatalog);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        recyclerViewCatalog.setLayoutManager(llm);

        RvAdapterCatalog adapter = new RvAdapterCatalog(allCategories);
        recyclerViewCatalog.setAdapter(adapter);
    }
}
