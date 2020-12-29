package com.example.old_school_store_app.views.catalog;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.catalog.ControllerCatalogProductInfoFragment;
import com.example.old_school_store_app.controllers.search.ControllerSearchProductInfoFragment;

public class CatalogProductInfoFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_catalog_product_info, container, false);

        ControllerCatalogProductInfoFragment controller = new ControllerCatalogProductInfoFragment(view);
        controller.InitializeButtonsClick();
        controller.FillInfoFields();

        return view;
    }
}
