package com.example.old_school_store_app.controllers.search;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.Product;
import com.example.old_school_store_app.models.entities.ProductPicture;

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

    public void ChooseMethodShowProducts()
    {
        if(DataStorage.ExistKey("searchText")==true)
        {
            String searchText = (String) DataStorage.Get("searchText");
            DataStorage.Delete("searchText");

            EditText editTextProductName = view.findViewById(R.id.editTextProductNameSearch);

            editTextProductName.setText(searchText);

            SearchMethod();
        }
        else
        {
            ShowAllProducts();
        }
    }

    public void ShowAllProducts()
    {
        ArrayList<Product> allProducts = db.GetTableProducts().GetAll();

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < allProducts.size(); i++)
        {
            int id = allProducts.get(i).getId();
            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(id);

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            allProducts.get(i).setMainPictureId(mainPicture);
        }

        RecyclerView recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        GridLayoutManager glm = new GridLayoutManager(context,2);
        recyclerViewSearch.setLayoutManager(glm);

        RvAdapterSearchProducts adapter = new RvAdapterSearchProducts(allProducts);
        recyclerViewSearch.setAdapter(adapter);
    }

    private void ResetSearch()
    {
        EditText editTextProductName = view.findViewById(R.id.editTextProductNameSearch);
        editTextProductName.setText("");

        DataStorage.Delete("searchText");

        ShowAllProducts();
    }

    private void SearchMethod()
    {
        EditText editTextProductName = view.findViewById(R.id.editTextProductNameSearch);
        String partOfName = editTextProductName.getText().toString();

        DataStorage.Add("searchText",partOfName);

        ArrayList<Product> foundedProducts = db.GetTableProducts().GetByPartOfName(partOfName);

        Context context = (Context) DataStorage.Get("context");

        for (int i = 0; i < foundedProducts.size(); i++)
        {
            int id = foundedProducts.get(i).getId();
            ProductPicture picture = db.GetTableProductsPictures().GetMainProductPicture(id);

            int mainPicture = context.getResources().getIdentifier(picture.getPicturePath(),"drawable", context.getPackageName());

            foundedProducts.get(i).setMainPictureId(mainPicture);
        }

        RecyclerView recyclerViewSearch = view.findViewById(R.id.recyclerViewSearch);
        GridLayoutManager glm = new GridLayoutManager(context,2);
        recyclerViewSearch.setLayoutManager(glm);

        RvAdapterSearchProducts adapter = new RvAdapterSearchProducts(foundedProducts);
        recyclerViewSearch.setAdapter(adapter);
    }
}
