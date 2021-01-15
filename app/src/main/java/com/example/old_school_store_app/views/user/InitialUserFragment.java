package com.example.old_school_store_app.views.user;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.user.ControllerInitialUserFragment;

public class InitialUserFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_initial_user, container, false);

        ControllerInitialUserFragment controller = new ControllerInitialUserFragment(view);
        controller.InitializeButtonsClick();

        return view;
    }
}
