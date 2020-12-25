package com.example.old_school_store_app.views.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.search.ControllerSearchFragment;

public class SearchFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ControllerSearchFragment controller = new ControllerSearchFragment(view);
        controller.InitializeButtonsClick();
        controller.ChooseMethodShowProducts();

        return view;
    }
}
