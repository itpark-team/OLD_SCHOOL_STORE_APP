package com.example.old_school_store_app.controllers.catalog;

import android.content.Context;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.search.RvAdapterProduct;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;

import java.util.ArrayList;

public class ControllerCategoryItemsFragment
{
    private View view;
    private DbManager db;

    public ControllerCategoryItemsFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void ShowProductsByCategory()
    {
        int categoryId = (int)DataStorage.Get("categoryId");

        ArrayList<Product> foundedProducts = db.GetTableProducts().GetByCategoryId(categoryId);

        DataStorage.Delete("categoryId");

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < foundedProducts.size(); i++)
        {
            int id = foundedProducts.get(i).getId();
            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(id);

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            foundedProducts.get(i).setMainPictureId(mainPicture);
        }

        RecyclerView recyclerViewCategoryItems = view.findViewById(R.id.recyclerViewCategoryItems);

        GridLayoutManager glm = new GridLayoutManager(context,2);
        recyclerViewCategoryItems.setLayoutManager(glm);

        RvAdapterProduct adapter = new RvAdapterProduct(foundedProducts);
        recyclerViewCategoryItems.setAdapter(adapter);

    }
}
