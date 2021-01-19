package com.example.old_school_store_app.controllers.tutorial;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ViewFlipper;

import androidx.core.app.ActivityCompat;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.models.DbManager;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.tutorial.TutorialActivity;

public class ControllerTutorialActivity
{
    private TutorialActivity tutorialActivity;

    private ViewFlipper viewFlipperPages;
    private int indexCurPage, countPages;

    private DbManager db;

    public ControllerTutorialActivity(TutorialActivity tutorialActivity)
    {
        this.tutorialActivity = tutorialActivity;
        db = DbManager.GetInstance(this.tutorialActivity.getApplicationContext());
    }

    public void CheckShowTutorial()
    {
        //db.GetTableSettingsApp().Add("showTutorial","0");

        String valueField = db.GetTableSettingsApp().Get("showTutorial");

        int show = Integer.parseInt(valueField);

        if(show==0)
        {
            OpenMainActivity();
        }
    }


    public void InitializeViewFlipper()
    {
        viewFlipperPages = tutorialActivity.findViewById(R.id.viewFlipperPages);

        LayoutInflater inflater = (LayoutInflater) tutorialActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int[] layoutIds = new int[]{
                R.layout.first_page,
                R.layout.second_page,
                R.layout.third_page,
                R.layout.fourth_page,
                R.layout.fifth_page,
                R.layout.sixth_page,
                R.layout.seventh_page,
        };

        for (int i = 0; i < layoutIds.length; i++)
        {
            viewFlipperPages.addView(inflater.inflate(layoutIds[i], null));
        }

        indexCurPage=0;
        countPages = layoutIds.length;
    }

    public void InitializeButtonsClick()
    {
        ImageButton b1 = tutorialActivity.findViewById(R.id.imageButtonToSecondPage);
        ImageButton b2 = tutorialActivity.findViewById(R.id.imageButtonToThirdPage);
        ImageButton b3 = tutorialActivity.findViewById(R.id.imageButtonToFourthPage);
        ImageButton b4 = tutorialActivity.findViewById(R.id.imageButtonToFifthPage);
        ImageButton b5 = tutorialActivity.findViewById(R.id.imageButtonToSixthPage);
        ImageButton b6 = tutorialActivity.findViewById(R.id.imageButtonToSeventhPage);

        ImageButton b7 = tutorialActivity.findViewById(R.id.imageButtonToMainPage);

        b1.setOnClickListener(OnButtonNextClick);
        b2.setOnClickListener(OnButtonNextClick);
        b3.setOnClickListener(OnButtonNextClick);
        b4.setOnClickListener(OnButtonNextClick);
        b5.setOnClickListener(OnButtonNextClick);
        b6.setOnClickListener(OnButtonNextClick);

        b7.setOnClickListener(OnButtonFinishClick);
    }

    private View.OnClickListener OnButtonNextClick = new View.OnClickListener()
    {
        @Override
        public void onClick(View view)
        {
            GoToNextPage();
        }
    };

    private View.OnClickListener OnButtonFinishClick = new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            OpenMainActivity();
        }
    };

    private void GoToNextPage()
    {
        if(indexCurPage<countPages-1)
        {
            viewFlipperPages.setInAnimation(AnimationUtils.loadAnimation(tutorialActivity.getApplicationContext(),R.anim.go_next_in));
            viewFlipperPages.setOutAnimation(AnimationUtils.loadAnimation(tutorialActivity.getApplicationContext(),R.anim.go_next_out));

            viewFlipperPages.showNext();
            indexCurPage++;
        }
    }

    private void OpenMainActivity()
    {
        Intent intent = new Intent(tutorialActivity.getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        tutorialActivity.startActivity(intent);
        ActivityCompat.finishAffinity(tutorialActivity);
    }

}
