package com.example.old_school_store_app.views.user;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.user.ControllerAuthUserFragment;

public class AuthUserFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.frament_auth_user, container, false);

        ControllerAuthUserFragment controller = new ControllerAuthUserFragment(view);
        controller.InitializeButtonsClick();

        return view;
    }
}
