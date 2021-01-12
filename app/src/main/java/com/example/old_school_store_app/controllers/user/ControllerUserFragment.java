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
import com.example.old_school_store_app.views.user.AuthUserFragment;
import com.example.old_school_store_app.views.user.RegisterUserFragment;

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

        AuthUserFragment authUserFragment = new AuthUserFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, authUserFragment);
        fragmentTransaction.commit();
    }

    private void GoToRegister()
    {
        MainActivity mainActivity = (MainActivity) DataStorage.Get("mainActivity");

        RegisterUserFragment registerUserFragment = new RegisterUserFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, registerUserFragment);
        fragmentTransaction.commit();
    }
}
