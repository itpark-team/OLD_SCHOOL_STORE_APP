package com.example.old_school_store_app.controllers.search;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;
import com.example.old_school_store_app.views.main.MainActivity;

import java.util.ArrayList;

public class ControllerSearchFragment
{
    private View view;
    private DbManager db;

    public ControllerSearchFragment(View view)
    {
        this.view = view;
        Context context = (Context) DataStorage.Get("context");
        db = DbManager.GetInstance(context);
    }

    public void InitializeButtonsClick()
    {
        Button buttonStartSearch = view.findViewById(R.id.buttonStartSearch);
        buttonStartSearch.setOnClickListener(OnButtonStartSearchClickListener);

        Button buttonResetSearch = view.findViewById(R.id.buttonResetSearch);
        buttonResetSearch.setOnClickListener(OnButtonResetSearchClickListener);
    }

    private View.OnClickListener OnButtonStartSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            SearchMethod();
        }
    };

    private View.OnClickListener OnButtonResetSearchClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            ResetSearch();
        }
    };

    public void ShowAllProducts()
    {
        ArrayList<Product> foundedProducts = db.GetTableProducts().GetAll();

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < foundedProducts.size(); i++)
        {
            int id = foundedProducts.get(i).getId();
            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(id);

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            foundedProducts.get(i).setMainPicture(mainPicture);
        }

        RecyclerView recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        GridLayoutManager glm = new GridLayoutManager(context,2);
        recyclerViewSearch.setLayoutManager(glm);

        RvAdapterSearch adapter = new RvAdapterSearch(foundedProducts);
        recyclerViewSearch.setAdapter(adapter);
    }

    private void ResetSearch()
    {
        EditText editTextProductName = view.findViewById(R.id.editTextProductNameSearch);
        editTextProductName.setText("");

        ShowAllProducts();
    }

    private void SearchMethod()
    {
        EditText editTextProductName = view.findViewById(R.id.editTextProductNameSearch);
        String partOfName = editTextProductName.getText().toString();

        ArrayList<Product> foundedProducts = db.GetTableProducts().GetByPartOfName(partOfName);

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < foundedProducts.size(); i++)
        {
            int id = foundedProducts.get(i).getId();
            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(id);

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            foundedProducts.get(i).setMainPicture(mainPicture);
        }

        RecyclerView recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        GridLayoutManager glm = new GridLayoutManager(context,2);
        recyclerViewSearch.setLayoutManager(glm);

        RvAdapterSearch adapter = new RvAdapterSearch(foundedProducts);
        recyclerViewSearch.setAdapter(adapter);
    }
}
