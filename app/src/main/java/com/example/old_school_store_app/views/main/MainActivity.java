package com.example.old_school_store_app.views.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.main.ControllerMainActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        ControllerMainActivity controller = new ControllerMainActivity(this);
        controller.SaveApplicationGlobalVariables();
        controller.InitializeFragments();
        controller.InitializeBottomNavigationView();
    }


}