package com.example.old_school_store_app.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.main.ControllerMainActivity;

public class MainActivity extends AppCompatActivity {

    private ControllerMainActivity controller;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        controller = new ControllerMainActivity(this);
        controller.InitializeFragments();
        controller.InitializeBottomNavigationView();
    }


}