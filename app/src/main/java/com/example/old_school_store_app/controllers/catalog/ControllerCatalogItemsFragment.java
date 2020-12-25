package com.example.old_school_store_app.controllers.catalog;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.search.RvAdapterSearchProducts;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.main.MainActivity;

import java.util.ArrayList;

public class ControllerCatalogItemsFragment
{
    private View view;
    private DbManager db;

    public ControllerCatalogItemsFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void ShowProductsByCategory()
    {
        int categoryId = (int)DataStorage.Get("categoryId");
        DataStorage.Delete("categoryId");

        ArrayList<Product> foundedProducts = db.GetTableProducts().GetByCategoryId(categoryId);

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

        RvAdapterCatalogItemProducts adapter = new RvAdapterCatalogItemProducts(foundedProducts);
        recyclerViewCategoryItems.setAdapter(adapter);

    }

    public void InitializeButtonsClick()
    {
        Button buttonGoBackToCatalog = view.findViewById(R.id.buttonGoBackToCatalog);
        buttonGoBackToCatalog.setOnClickListener(OnButtonGoBackToCatalogClickListener);
    }

    private View.OnClickListener OnButtonGoBackToCatalogClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GoToBackMethod();
        }
    };


    private void GoToBackMethod()
    {
        CatalogFragment catalogFragment = new CatalogFragment();

        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogFragment);
        fragmentTransaction.commit();
    }
}
