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

public class TutorialActivity extends AppCompatActivity {

    private ControllerTutorialActivity controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tutorial);

        controller = new ControllerTutorialActivity(this);
        controller.InitializeViewFlipper();
    }

    public void onButtonNextClick(View view)
    {
        controller.GoToNextPage();
    }

    public void onButtonFinishClick(View view)
    {
        controller.OpenMainActivity();
    }


}
