package com.example.old_school_store_app.views.user;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.user.ControllerShowUserFragment;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.models.entities.User;

public class ShowUserFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_show_user, container, false);

        ControllerShowUserFragment controller = new ControllerShowUserFragment(view);
        controller.ShowUserFields();
        controller.InitializeButtonsClick();

        return view;
    }
}
