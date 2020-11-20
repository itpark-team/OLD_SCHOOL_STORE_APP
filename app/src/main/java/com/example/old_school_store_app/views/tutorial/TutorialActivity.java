package com.example.old_school_store_app.views.tutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.old_school_store_app.R;

public class TutorialActivity extends AppCompatActivity {

    ViewFlipper viewFlipperPages;
    int indexCurPage, countPages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();//отключил верхнюю плашку
        setContentView(R.layout.activity_tutorial);

        Toolbar toolbar = findViewById(R.id.toolBarMain);//tool bar

        //setSupportActionBar(toolbar);

        viewFlipperPages = findViewById(R.id.viewFlipperPages);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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

    public void onButtonNextClick(View view)
    {
        if(indexCurPage<countPages-1)
        {
            viewFlipperPages.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.go_next_in));
            viewFlipperPages.setOutAnimation(AnimationUtils.loadAnimation(getApplicationContext(),R.anim.go_next_out));

            viewFlipperPages.showNext();
            indexCurPage++;
        }
    }


}
