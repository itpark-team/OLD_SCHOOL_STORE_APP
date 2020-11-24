package com.example.old_school_store_app.views.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.views.cart.CartFragment;
import com.example.old_school_store_app.views.catalog.CatalogFragment;
import com.example.old_school_store_app.views.map.MapFragment;
import com.example.old_school_store_app.views.search.SearchFragment;
import com.example.old_school_store_app.views.user.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private UserFragment userFragment;
    private SearchFragment searchFragment;
    private CatalogFragment catalogFragment;
    private MapFragment mapFragment;
    private CartFragment cartFragment;

    private BottomNavigationView bottomNavigationViewMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        userFragment = new UserFragment();
        searchFragment = new SearchFragment();
        catalogFragment = new CatalogFragment();
        mapFragment = new MapFragment();
        cartFragment = new CartFragment();

        FragmentTransaction fragmentTransaction;
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragmentsContainerMain, userFragment);
        fragmentTransaction.commit();

        bottomNavigationViewMain = findViewById(R.id.bottomNavigationViewMain);
        bottomNavigationViewMain.setOnNavigationItemSelectedListener(bottomNavigationViewItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener bottomNavigationViewItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            FragmentTransaction fragmentTransaction;
            fragmentTransaction = getFragmentManager().beginTransaction();

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