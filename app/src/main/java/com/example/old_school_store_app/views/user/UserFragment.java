package com.example.old_school_store_app.views.user;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.user.ControllerUserFragment;

public class UserFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        ControllerUserFragment controller = new ControllerUserFragment(view);
        controller.InitializeButtonsClick();

        return view;
    }
}
