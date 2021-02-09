package com.example.old_school_store_app.views.map;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.old_school_store_app.R;
import com.example.old_school_store_app.controllers.map.ControllerMapFragment;
import com.example.old_school_store_app.models.DataStorage;
import com.example.old_school_store_app.views.main.MainActivity;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.map.MapObject;
import com.yandex.mapkit.map.MapObjectCollection;
import com.yandex.mapkit.map.MapObjectTapListener;
import com.yandex.mapkit.map.PlacemarkMapObject;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.runtime.image.ImageProvider;

public class MapFragment extends Fragment
{
    private ControllerMapFragment controller;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        controller = new ControllerMapFragment(view);
        controller.CreateMapObjects();

        return view;
    }


    @Override
    public void onStop() {
        controller.OnStop();
        super.onStop();
    }

    @Override
    public void onStart() {
        super.onStart();
        controller.OnStart();
    }
}
