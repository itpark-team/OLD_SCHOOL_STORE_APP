package com.example.old_school_store_app.controllers.main;

import android.app.FragmentTransaction;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.views.cart.CartFragment;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.map.MapFragment;
import com.example.old_school_store_app.views.search.SearchFragment;
import com.example.old_school_store_app.views.user.InitialUserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerMainActivity
{
    private MainActivity mainActivity;

    private InitialUserFragment initialUserFragment;
    private SearchFragment searchFragment;
    private CatalogFragment catalogFragment;
    private MapFragment mapFragment;
    private CartFragment cartFragment;

    private BottomNavigationView bottomNavigationViewMain;

    public ControllerMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void SaveApplicationGlobalVariables()
    {
        DataStorage.Add("context", mainActivity.getApplicationContext());
        DataStorage.Add("mainActivity", mainActivity);
    }

    public void InitializeFragments()
    {
        initialUserFragment = new InitialUserFragment();
        searchFragment = new SearchFragment();
        catalogFragment = new CatalogFragment();
        mapFragment = new MapFragment();
        cartFragment = new CartFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
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
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, initialUserFragment);
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
