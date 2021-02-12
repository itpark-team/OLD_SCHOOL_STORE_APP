package com.example.old_school_store_app.views.cart;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.cart.ControllerCartFragment;

public class CartFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        ControllerCartFragment controller = new ControllerCartFragment(view);

        controller.InitializeButtonsClick();
        controller.FillUserFields();
        controller.ShowCartProducts();

        return view;
    }
}
