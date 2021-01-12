package com.example.old_school_store_app.controllers.user;

import android.app.FragmentTransaction;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.main.MainActivity;

public class ControllerUserFragment
{
    private View view;

    public ControllerUserFragment(View view)
    {
        this.view = view;
    }

    public void InitializeButtonsClick()
    {
        Button buttonUserRegister = view.findViewById(R.id.buttonUserRegister);
        buttonUserRegister.setOnClickListener(OnButtonUserRegisterClickListener);

        Button buttonUserEnter = view.findViewById(R.id.buttonUserEnter);
        buttonUserEnter.setOnClickListener(OnButtonUserEnterClickListener);
    }

    private View.OnClickListener OnButtonUserRegisterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GoToRegister();
        }
    };

    private View.OnClickListener OnButtonUserEnterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            GoToAuth();
        }
    };

    private void GoToAuth()
    {
        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        CatalogFragment catalogFragment = new CatalogFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogFragment);
        fragmentTransaction.commit();
    }

    private void GoToRegister()
    {

    }
}
