package com.example.old_school_store_app.views.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.Fragment;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.user.ControllerShowUserFragment;
import com.example.old_school_store_app.controllers.user.ControllerUserOrderFragment;

public class UserOrdersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_user_orders, container, false);

        ControllerUserOrderFragment controller = new ControllerUserOrderFragment(view);
        controller.ShowOrdersView();
        controller.InitializeButtonsClick();

        return view;
    }
}
