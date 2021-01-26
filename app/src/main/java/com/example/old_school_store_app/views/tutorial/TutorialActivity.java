package com.example.old_school_store_app.views.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.tutorial.ControllerTutorialActivity;
import com.example.old_school_store_app.views.main.MainActivity;
import com.yandex.mapkit.MapKitFactory;

public class TutorialActivity extends AppCompatActivity {

    private final String MAPKIT_API_KEY = "d78085c2-d63e-4e65-9dd9-0978a258e302";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        MapKitFactory.setApiKey(MAPKIT_API_KEY);
        MapKitFactory.initialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tutorial);

        ControllerTutorialActivity controller = new ControllerTutorialActivity(this);

        controller.CheckShowTutorial();

        controller.InitializeViewFlipper();
        controller.InitializeButtonsClick();

    }

}
