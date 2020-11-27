package com.example.old_school_store_app.controllers.main;

import android.app.FragmentTransaction;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.views.cart.CartFragment;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.map.MapFragment;
import com.example.old_school_store_app.views.search.SearchFragment;
import com.example.old_school_store_app.views.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ControllerMainActivity
{
    private MainActivity mainActivity;

    private UserFragment userFragment;
    private SearchFragment searchFragment;
    private CatalogFragment catalogFragment;
    private MapFragment mapFragment;
    private CartFragment cartFragment;

    private BottomNavigationView bottomNavigationViewMain;

    public ControllerMainActivity(MainActivity mainActivity)
    {
        this.mainActivity = mainActivity;
    }

    public void InitializeFragments()
    {
        userFragment = new UserFragment();
        searchFragment = new SearchFragment();
        catalogFragment = new CatalogFragment();
        mapFragment = new MapFragment();
        cartFragment = new CartFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = mainActivity.getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, userFragment);
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
                case R.id.action_user:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, userFragment);
                    break;
                case R.id.action_search:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, searchFragment);
                    break;
                case R.id.action_catalog:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, catalogFragment);
                    break;
                case R.id.action_map:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, mapFragment);
                    break;
                case R.id.action_cart:
                    fragmentTransaction.replace(R.id.fragmentsContainerMain, cartFragment);
                    break;
            }

            fragmentTransaction.commit();

            return true;
        }
    };


}
