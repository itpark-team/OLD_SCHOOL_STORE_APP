package com.example.old_school_store_app.controllers.tutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.animation.AnimationUtils;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.views.main.MainActivity;
import com.example.old_school_store_app.views.tutorial.TutorialActivity;

public class ControllerTutorialActivity
{
    private TutorialActivity tutorialActivity;

    ViewFlipper viewFlipperPages;
    int indexCurPage, countPages;

    public ControllerTutorialActivity(TutorialActivity tutorialActivity)
    {
        this.tutorialActivity = tutorialActivity;
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

    public void GoToNextPage()
    {
        if(indexCurPage<countPages-1)
        {
            viewFlipperPages.setInAnimation(AnimationUtils.loadAnimation(tutorialActivity.getApplicationContext(),R.anim.go_next_in));
            viewFlipperPages.setOutAnimation(AnimationUtils.loadAnimation(tutorialActivity.getApplicationContext(),R.anim.go_next_out));

            viewFlipperPages.showNext();
            indexCurPage++;
        }
    }

    public void OpenMainActivity()
    {
        Intent intent = new Intent(tutorialActivity.getApplicationContext(), MainActivity.class);
        tutorialActivity.startActivity(intent);
    }

}
