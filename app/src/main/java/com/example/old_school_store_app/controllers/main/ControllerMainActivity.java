package com.example.old_school_store_app.controllers.main;

import android.app.FragmentTransaction;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.models.entities.User;
import com.example.old_school_store_app.views.cart.CartFragment;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.map.MapFragment;
import com.example.old_school_store_app.views.search.SearchFragment;
import com.example.old_school_store_app.views.user.InitialUserFragment;
import com.example.old_school_store_app.views.user.ShowUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerMainActivity
{
    private MainActivity mainActivity;

    private DbManager db;

    private ShowUserFragment showUserFragment;
    private InitialUserFragment initialUserFragment;
    private SearchFragment searchFragment;
    private CatalogFragment catalogFragment;
    private MapFragment mapFragment;
    private CartFragment cartFragment;

    private BottomNavigationView bottomNavigationViewMain;

    public ControllerMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;

        db = DbManager.GetInstance(this.mainActivity.getApplicationContext());
    }



    public void SaveApplicationGlobalVariables()
    {
        DataStorage.Add("context", mainActivity.getApplicationContext());
        DataStorage.Add("mainActivity", mainActivity);
    }

    public void CheckAuthorizedUser()
    {
        if(db.GetTableSettingsApp().ExistKey("authorizedUser")==true)
        {
            String valueField = db.GetTableSettingsApp().Get("authorizedUser");

            int idUser = Integer.parseInt(valueField);

            User user = db.GetTableUsers().GetById(idUser);

            DataStorage.Add("authorizedUser", user);
        }
    }

    public void InitializeFragments()
    {
        initialUserFragment = new InitialUserFragment();
        showUserFragment = new ShowUserFragment();

        searchFragment = new SearchFragment();
        catalogFragment = new CatalogFragment();
        mapFragment = new MapFragment();
        cartFragment = new CartFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();

        if(DataStorage.ExistKey("authorizedUser")==true)
        {
            fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
        }
        else
        {
            fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
        }


        fragmentTransaction.commit();
    }

    public void InitializeBottomNavigationView()
    {
        bottomNavigationViewMain = mainActivity.findViewById(R.id.bottomNavigationViewMain);
        bottomNavigationViewMain.setOnNavigationItemSelectedListener(bottomNavigationViewItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationViewItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            FragmentTransaction fragmentTransaction;
            fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();

            switch (item.getItemId())
            {
                case R.id.actionUser:
                    if(DataStorage.ExistKey("authorizedUser")==true)
                    {
                        fragmentTransaction.replace(R.id.fragmentsContainerMain, showUserFragment);
                    }
                    else
                    {
                        fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
                    }
                    break;
                case R.id.actionSearch:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, searchFragment);
                    break;
                case R.id.actionCatalog:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogFragment);
                    break;
                case R.id.actionMap:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, mapFragment);
                    break;
                case R.id.actionCart:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, cartFragment);
                    break;
            }

            fragmentTransaction.commit();

            return true;
        }
    };


}
